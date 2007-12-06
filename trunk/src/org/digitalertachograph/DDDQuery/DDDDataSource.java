/*   Copyright (C) 2007, Martin Barth

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

import org.digitalertachograph.DDDQuery.internalData.DataClass;
import org.digitalertachograph.DDDQuery.internalData.TachographData;

import sun.io.Converters;

public class DDDDataSource implements DataSource {
	
	String src;
	TachographData td = new TachographData();

	public TachographData getTachographData() {
		return td;
	}

	public void setSourceFile(String src) {
		this.src = src;
		readSourceFile();
	}
	
	public void setSource(byte[] src){
		readSource(src);
	}

	private void readSource(byte[] src){
		td = new TachographData(); // wipe
		int pos = 0;
		while(true){
			byte[] tag = new byte[3];
			byte[] length = new byte[2];
			int length_i = 0;
				
			byte[] value;
			
			if(src.length < pos + 3){
				break;
			}
			System.arraycopy(src, pos, tag, 0, 3);
			pos += 3;
			
			if(src.length < pos + 2){
				break;
			}
			System.arraycopy(src, pos, length, 0, 2);
			pos += 2;
			
			length_i = calculateLength(length);
			value = new byte[length_i];
			if(src.length < pos + length_i){
				break;
			}
			System.arraycopy(src, pos, value, 0, length_i);
			pos += length_i;

			td.add(tag, length, value);
		}	
		td.printTL();
		
	}
	
	private void readSourceFile(){
		File f = new File(src);
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

		
		readSource(s);
	}
	
	private int calculateLength(byte[] b){

		int i = 0;
		
		i += (b[0] & 0xFF) << 8;
		i += (b[1] & 0xFF);
		return i;
		
//		int length_i = 0;
//		int till = 1;
//		for(int i = 0; i <= till; i++){
//			int tmp;
//			if(length[i] >= 0){
//				// the normal way..
//				tmp = length[i];
//			}else{
//				// get rid of the algebraic sign
//				tmp = 256 + length[i];
//			}
//
////			shift an die passende possition
//			tmp = tmp<<((till-i)*8);
//			length_i += tmp;
//		}
//		return length_i;
	}
}
