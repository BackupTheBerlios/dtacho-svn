/*
    $Id$

    Copyright (C) 2008, Gerald Schnabel

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

import java.io.*;

/**
 * Class for outputting debug messages.
 */
public class DebugLogger {

	/**
	 * Debug loglevel NOTHING: nothing will be logged
	 */
	public final static int LOGLEVEL_NOTHING = 0;
	/**
	 * Debug loglevel ERROR: errors will be logged
	 */
	public final static int LOGLEVEL_ERROR = 1;
	/**
	 * Debug loglevel INFO: information will be logged
	 */
	public final static int LOGLEVEL_INFO = 2;
	/**
	 * Debug loglevel INFO_EXTENDED: extended information will be logged
	 */
	public final static int LOGLEVEL_INFO_EXTENDED = 4;
	/**
	 * Debug loglevel ALL: every output will be logged
	 */
	public final static int LOGLEVEL_ALL = 8;

	/**
	 * The log level for outputting messages
	 */
	public static int logLevel;
	/**
	 * The printstream where messages will be put
	 */
	public static PrintStream logPrintStream;

	private static int printDelayedLevel;
	private static String printDelayedString;
	private static boolean printDelayedOnHold = false;
	private static boolean printDelayedDone = false;

	/**
	 * Constructor for a DebugLogger object.
	 * 
	 * The static settings must be either set with the corresponding
	 * setter routines of by {@link #InitEnv()}.
	 */
	public DebugLogger() {

	}

	/**
	 * Returns the log level of the DebugLogger.
	 * 
	 * @return	the log level
	 */
	public static int getLogLevel() {
		return logLevel;
	}

	/**
	 * Sets the log level of the DebugLogger.
	 * 
	 * @param	pLogLevel	the log level to be set
	 */
	public static void setLogLevel( int pLogLevel) {
		logLevel = pLogLevel;
	}

	/**
	 * Returns the printstream of the DebugLogger.
	 * 
	 * @return	the printstream
	 */
	public static PrintStream getLogPrintStream() {
		return logPrintStream;
	}

	/**
	 * Sets the printstream of the DebugLogger.
	 * 
	 * @param	pLogPrintStream	the printstream to be set
	 */
	public static void setLogPrintStream( PrintStream pLogPrintStream) {
		logPrintStream = pLogPrintStream;
	}

	/**
	 * Initialises the static settings for the DebugLogger from environment variables.
	 *
	 * DEBUGLOGLEVEL: sets the debug log level
	 * DEBUGPRINTSTREAM: sets the printstream where the debug messages are put.
	 */
	public static void InitEnv() {
		// try to read log level from environment variable
		// defaults to LOGLEVEL_ERROR
		logLevel = LOGLEVEL_ERROR;
		try {
			String envLogLevel = System.getenv( "DEBUGLOGLEVEL" );

			if ( envLogLevel != null ) {
				// LOGLEVEL_NOTHING
				if ( envLogLevel.toUpperCase().compareTo( "NOTHING" ) == 0 ) {
					logLevel = LOGLEVEL_NOTHING;
				}
				// LOGLEVEL_ERROR
				if ( envLogLevel.toUpperCase().compareTo( "ERROR" ) == 0 ) {
					logLevel = LOGLEVEL_ERROR;
				}
				// LOGLEVEL_INFO
				if ( envLogLevel.toUpperCase().compareTo( "INFO" ) == 0 ) {
					logLevel = LOGLEVEL_INFO;
				}
				// LOGLEVEL_INFO_EXTENDED
				if ( envLogLevel.toUpperCase().compareTo( "INFO_EXTENDED" ) == 0 ) {
					logLevel = LOGLEVEL_INFO_EXTENDED;
				}
				// LOGLEVEL_ALL
				if ( envLogLevel.toUpperCase().compareTo( "ALL" ) == 0 ) {
					logLevel = LOGLEVEL_ALL;
				}
			}
		}
		catch ( NullPointerException npe ) {
			npe.printStackTrace();
		}
		catch ( SecurityException se ) {
			se.printStackTrace();
		}

		// try to read printstream for logging from environment variable
		// defaults to System.out
		logPrintStream = System.out;
		try {
			String envPrintStream = System.getenv( "DEBUGPRINTSTREAM" );

			if ( envPrintStream != null ) {
				FileOutputStream envPrintStreamFile = new FileOutputStream( envPrintStream );
				logPrintStream = new PrintStream( envPrintStreamFile, true );
			}
		}
		catch ( FileNotFoundException fnfe ) {

		}
		catch ( NullPointerException npe ) {
			npe.printStackTrace();
		}
		catch ( SecurityException se ) {
			se.printStackTrace();
		}

	}

	/**
	 * Prints the given char for the given debug level.
	 * 
	 * @param	level	the debug level
	 * @param	c		the char to be printed
	 */
	public void print( int level, char c ) {
		if ( logLevel >= level ) {
			printDelayed();
			logPrintStream.print( c );
		}
	}

	/**
	 * Prints the given string for the given debug level.
	 * 
	 * @param	level	the debug level
	 * @param	s		the string to be printed
	 */
	public void print( int level, String s ) {
		if ( logLevel >= level ) {
			printDelayed();
			logPrintStream.print( s );
		}
	}

	/**
	 * Prints the given formatted string with arguments for the given debug level.
	 * 
	 * @param	level	the debug level
	 * @param	format	the formatted string to be printed
	 * @param	args	the arguments for the formatted string
	 */
	public void printf( int level, String format, Object... args ) {
		if ( logLevel >= level ) {
			printDelayed();
			logPrintStream.printf( format, args );
		}
	}

	/**
	 * Prints the given string followed by a new line for the given debug level.
	 * 
	 * @param	level	the debug level
	 * @param	x		the string to be printed
	 */
	public void println( int level, String x ) {
		if ( logLevel >= level ) {
			printDelayed();
			logPrintStream.println( x );
		}
	}

	/**
	 * Puts the given string followed by a new line for the given debug level on hold
	 * until the next print function is called. Then the string will be put to the
	 * print stream.
	 * 
	 * @param	level	the debug level
	 * @param	x		the string to be printed
	 * @param	b		indicates delayed printing
	 */
	public void println( int level, String x, boolean b ) {
		if ( b != true ) {
			this.println( level, x );
		}
		else {
			printDelayedLevel = level;
			printDelayedString = x;
			printDelayedOnHold = true;
		}
	}

	/**
	 * Prints a new line for the given debug level.
	 * 
	 * @param	level	the debug level
	 */
	public void println( int level ) {
		if ( logLevel >= level ) {
			printDelayed();
			logPrintStream.println();
		}
	}

	/**
	 * Resets delayed printing
	 */
	public void resetPrintDelayed() {
		printDelayedOnHold = false;
		printDelayedDone = false;
	}

	private void printDelayed() {
		if ( ( printDelayedOnHold == true ) && ( printDelayedDone == false ) ) {
			printDelayedDone = true;
			this.println( printDelayedLevel, printDelayedString );
		}
	}
}
