/*   Copyright (C) 2007, Martin Barth, Gerald Schnabel

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if (not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package org.digitalertachograph.DDDQuery;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.digitalertachograph.DDDQuery.internalData.TachographData;

public class DDDDataSource implements DataSource {
	
	private byte[] src;
	private String srcfile;
	private TachographData td = new TachographData();

	/**
	 * Constructor for a DDDDataSource object
	 */
	public DDDDataSource() {

	}
	
	/**
	 * Sets the source byte array with data of a .ddd file.
	 * The byte array will be parsed and a {@link TachographData}
	 * object will be set up internally. The TachographData
	 * object can then be copied with the {@link #getTachographData()}
	 * method.
	 *
	 * @param	src		byte array with data of a .ddd file
	 */
	public void setSource(byte[] src) {
		this.src = src;
	}

	public boolean processSource() {
		return readSource(src);
	}
	
	/**
	 * Returns the tachograph data.
	 * 
	 * @return			TachographData with content of the DDD source
	 */
	public TachographData getTachographData() {
		return td;
	}

	/**
	 * Sets the source .ddd file. The file will be parsed and a
	 * {@link TachographData} object will be set up internally.
	 * The TachographData object can then be copied with the
	 * {@link #getTachographData()} method.
	 *
	 * @param	srcfile		the location of the .ddd file that will be processed
	 */
	public void setSourceFile(String srcfile) {
		this.srcfile = srcfile;
	}

	public boolean processSourceFile() {
		return readSourceFile();
	}

	private boolean readSource(byte[] src) {
		boolean parseresult = false;
		td = new TachographData();
		int pos = 0;

		// data parser
		while(true){
			byte[] tag = new byte[3];
			byte[] length = new byte[2];
			int length_i = 0;
				
			byte[] value;
			
			// tag parser
			while(true) {
				if(src.length < pos + 3){
					// end of stream
					break;
				}

				System.arraycopy(src, pos, tag, 0, 3);
				pos += 3;

				if ( td.isValidFileID(tag) == false ) {
					if(Arrays.equals(new byte[]{tag[0],tag[1]}, new byte[]{0x76,0x06})) {
						// OPTAC download tools with firmware < v2.3 write two bytes (76 06, SID/TREP?!) at the
						// beginning of a .DDD file that are out of specs...
						System.out.println(" [INFO] data 76 06 (SID/TREP?!, OPTAC FW < v2.3 ) found, skipping...");
						pos -= 1;
					} else {
						System.out.printf("invalid tag, %02x %02x %02x\n", tag[0], tag[1], tag[2]);
						pos -= 2;
					}
				} else {
					parseresult = true;
					break;
				}
			}
				
			if (parseresult == false)
				break;

			if(src.length < pos + 2){
				break;
			}

			System.arraycopy(src, pos, length, 0, 2);
			pos += 2;
			
			length_i = calculateLength(length);
			value = new byte[length_i];
			if(src.length < pos + length_i){
				parseresult = false;
				break;
			}
			System.arraycopy(src, pos, value, 0, length_i);
			pos += length_i;

			parseresult = td.add(tag, length, value);
			if(parseresult != true)
				break;
		}	

		if(parseresult == true) {
			System.out.println("internal tag structure:");
			td.printTL();
			return true;
		}

		return false;
	}
		
	private boolean readSourceFile() {
		File f = new File(srcfile);
		FileInputStream fin = null;
		byte[] s = null;

		try
		{
			int length = (int)f.length();

			if (length < 0)
			{
				throw new Exception("foo");
			}

			s = new byte[length];
			fin = new FileInputStream(f);

			int bytesRead = 0;

			while (bytesRead < length)
			{
				int toRead = fin.available();
				fin.read(s, bytesRead, bytesRead + toRead);
				bytesRead += toRead;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return readSource(s);
	}
	
	private int calculateLength(byte[] b) {
		return ((b[0] & 0xff) << 8) + (b[1] & 0xff);
	}
}
