package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

public class Braille8SpecialChars {
	BrailleToRoman btr;
	
	public Braille8SpecialChars(BrailleToRoman btr) {
		this.btr = btr;
	}
	
	public void handleSpecialChars(char c) {
		switch (c) {
		
		// 0 - 112 => C0 Control Codes
		case   0: handleNUL(); break; // NUL -> will be appended
		case   1: handleSOH(); break; // SOH
		case   2: handleSTX(); break; // STX
		case   3: handleETX(); break; // ETX
		case   4: handleEOT(); break; // EOT
		case   5: handleENQ(); break; // ENQ
		case   6: handleACK(); break; // ACK
		case   7: handleBEL(); break; // BEL
		case   8: handleBS();  break; // BS
		case   9: handleTAB(); break; // TAB
		case  10: handleLF();  break; // LF
		case  11: handleVT();  break; // VT
		case  12: handleFF();  break; // FF
		case  13: handleCR();  break; // CR
		case  14: handleSO();  break; // SO
		case  15: handleSI();  break; // SI
		case  16: handleDLE(); break; // DLE
		case  17: handleDC1(); break; // DC1
		case  18: handleDC2(); break; // DC2
		case  19: handleDC3(); break; // DC3
		case  20: handleDC4(); break; // DC4
		case  21: handleNAK(); break; // NAK
		case  22: handleSYN(); break; // SYN
		case  23: handleETB(); break; // ETB
		case  24: handleCAN(); break; // CAN
		case  25: handleEM();  break; // EM
		case  26: handleSUB(); break; // SUB
		case  27: handleESC(); break; // ESC
		case  28: handleFS();  break; // FS
		case  29: handleGS();  break; // GS
		case  30: handleRS();  break; // RS
		case  31: handleUS();  break; // US
		case 112: handleDEL(); break; // DEL
		
		// 128 - 159 => C1 Control Codes
		case 128: handlePAD(); break; // PAD
		case 129: handleHOP(); break; // HOP
		case 130: handleBPH(); break; // BPH
		case 131: handleNBH(); break; // NBH
		case 132: handleIND(); break; // IND
		case 133: handleNEL(); break; // NEL
		case 134: handleSSA(); break; // SSA
		case 135: handleESA(); break; // ESA
		case 136: handleHTS(); break; // HTS
		case 137: handleHTJ(); break; // HTJ
		case 138: handleVTS(); break; // VTS
		case 139: handlePLD(); break; // PLD
		case 140: handlePLU(); break; // PLU
		case 141: handleRI();  break; // RI 
		case 142: handleSS2(); break; // SS2
		case 143: handleSS3(); break; // SS3
		case 144: handleDCS(); break; // DCS
		case 145: handlePU1(); break; // PU1
		case 146: handlePU2(); break; // PU2
		case 147: handleSTS(); break; // STS
		case 148: handleCCH(); break; // CCH
		case 149: handleMW();  break; // MW
		case 150: handleSPA(); break; // SPA
		case 151: handleEPA(); break; // EPA
		case 152: handleSOS(); break; // SOS
		case 153: handleSGCI();break; // SGCI
		case 154: handleSCI(); break; // SCI
		case 155: handleCSI(); break; // CSI
		case 156: handleST();  break; // ST
		case 157: handleOSC(); break; // OSC
		case 158: handlePM();  break; // PM
		case 159: handleAPC(); break; // APC
		
		default: btr.append(c);
		}
	}
	
	// C0 Control Codes
	private void handleNUL() {
		// NUL is ignored
		
		// Description:
		// 
		// First character of a message header.
	}

	private void handleSOH() {
		// Start of Heading is ignored
		
		// Description:
		// 
		// First character of a message header.
	}

	private void handleSTX() {
		// Start of Text is ignored
		
		// Description:
		// 
		// First character of message text, and may be used to
		// terminate the message heading.
	}

	private void handleETX() {
		// End Of Text is ignored
		
		// Description:
		//
		// Often used as a "break" character (Ctrl-C) to 
		// interrupt or terminate a program or process.
	}

	private void handleEOT() { 
		// End Of Transmission is ignored
		
		// Description:
		//
		// Used on Unix to signal end-of-file condition on, 
		// or to logout from a terminal.
	}

	private void handleENQ() {
		// Enquiry is ignored
		
		// Description:
		//
		// Signal intended to trigger a response at the receiving end,
		// to see if it is still present.
	}

	private void handleACK() {
		// Acknowledge is ignored
		
		// Description:
		// 
		// Response to an ENQ, or an indication of successful receipt of a message.
	}

	private void handleBEL() {
		// Bell is ignored
		
		// Description:
		// 
		// Originally used to sound a bell on the terminal. Later used 
		// for a beep on systems that didn't have a physical bell. 
		// May also quickly turn on and off inverse video (a visual bell).
	}

	private void handleBS() {
		// Backspace
		btr.deleteLastChar();
	}

	private void handleTAB() {
		// Tabulator
		btr.append('\t');
	}

	private void handleLF() {
		// Line Feed
		btr.append('\n');
	}

	private void handleVT() {
		// Vertical Tabulation is ignored --> '\v' is an invalid escape sequence
	}

	private void handleFF() {
		// Form Feed
		btr.append('\f');
	}

	private void handleCR() {
		// Carriage Return
		btr.append('\r');
	}

	private void handleSO() {
		// Shift Out is ignored
		
		// Description:
		// 
		// Switch to an alternate character set.
	}

	private void handleSI() {
		// Shift In is ignored
		
		// Description:
		// 
		// Return to regular character set after Shift Out.
	}

	private void handleDLE() {
		// Data Link Escape is ignored
		
		// Description:
		//
		// Cause the following octets to be interpreted as raw data, 
		// not as control codes or graphic characters. Returning to 
		// normal usage would be implementation dependent.
	}

	private void handleDC1() {
		handleDC4();
	}

	private void handleDC2() {
		handleDC4();
	}

	private void handleDC3() {
		handleDC4();
	}

	private void handleDC4() {
		// Device Control (1-4) is ignored
		
		// Description:
		//
		// These four control codes are reserved for device control, 
		// with the interpretation dependent upon the device they were
		// connected. DC1 and DC2 were intended primarily to indicate 
		// activating a device while DC3 and DC4 were intended primarily 
		// to indicate pausing or turning off a device. In actual practice 
		// DC1 and DC3 (known also as XON and XOFF respectively in this 
		// usage) quickly became the de facto standard for software 
		// flow control.
	}

	private void handleNAK() {
		// Negative Acknowledge is ignored
		
		// Description:
		//
		// Sent by a station as a negative response to the station with
		// which the connection has been set up. In binary synchronous 
		// communication protocol, the NAK is used to indicate that an 
		// error was detected in the previously received block and that 
		// the receiver is ready to accept retransmission of that block. 
		// In multipoint systems, the NAK is used as the not-ready reply 
		// to a poll.
	}

	private void handleSYN() {
		// Synchronous Idle is ignored
		
		// Description:
		//
		// Used in synchronous transmission systems to provide a signal
		// from which synchronous correction may be achieved between 
		// data terminal equipment, particularly when no other character
		// is being transmitted.
	}

	private void handleETB() {
		// End Of Transmission Block is ignored
		
		//
		// Description:
		//
		// Indicates the end of a transmission block of data when data
		// are divided into such blocks for transmission purposes.
	}

	private void handleCAN() {
		// Cancel is ignored
		
		//
		// Description:
		// 
		// Indicates that the data preceding it are in error or are to be disregarded.
	}

	private void handleEM() {
		// End Of Medium is ignored
		
		//
		// Description:
		//
		// Intended as means of indicating on paper or magnetic tapes that 
		// the end of the usable portion of the tape had been reached.
	}

	private void handleSUB() {
		// Substitute is ignored
		
		// 
		// Description:
		// 
		// Originally intended for use as a transmission control character 
		// to indicate that garbled or invalid characters had been received. 
		// It has often been put to use for other purposes when the in-band 
		// signaling of errors it provides is unneeded, especially where 
		// robust methods of error detection and correction are used, or where
		// errors are expected to be rare enough to make using the character 
		// for other purposes advisable.
	}

	private void handleESC() {
		// Escape is ignored. 
		
		// Could be used to delete text or exit the program
		// (with user interaction -> ask if its meant)
	}

	private void handleFS() {
		handleUS();
	}

	private void handleGS() {
		handleUS();
	}

	private void handleRS() {
		handleUS();
	}

	private void handleUS() {
		// Unit Separator, Record Separator, Group Separator and File Separator are ignored
		
		// 
		// Description:
		// 
		// Can be used as delimiters to mark fields of data structures. 
		// If used for hierarchical levels, US is the lowest level 
		// (dividing plain-text data items), while RS, GS, and FS are of 
		// increasing level to divide groups made up of items of the level 
		// beneath it.
	}

	private void handleDEL() {
		// DEL
		btr.deleteLastChar();
	}
	
	// C1 Control Codes
	
	private void handlePAD() {
		// Padding Character is ignored
	}

	private void handleHOP() {
		// High Octed Preset is ignored
	}

	private void handleBPH() {
		// Breaks Permitted Here is ignored
		
		// Description:
		//
		// Follows a graphic character where a line break is permitted. 
		// Roughly equivalent to a soft hyphen except that the means for 
		// indicating a line break is not necessarily a hyphen. Not part
		// of the first edition of ISO/IEC 6429.[1]
	}

	private void handleNBH() {
		// No Break Here is ignored
		
		// Description:
		// 
		// Follows the graphic character that is not to be broken. 
		// Not part of the first edition of ISO/IEC 6429.[1]
	}

	private void handleIND() {
		// Index is ignored
		
		// Description:
		// 
		// Move the active position one line down, to eliminate 
		// ambiguity about the meaning of LF. Deprecated in 1988 
		// and withdrawn in 1992 from ISO/IEC 6429 (1986 and 1991 
		// respectively for ECMA-48).
	}

	private void handleNEL() {
		// Next Line
		
		// Description:
		// 
		// Equivalent to CR+LF. Used to mark end-of-line on some IBM mainframes.
		
		btr.append("\r\n");
	}

	private void handleSSA() {
		handleESA();
	}

	private void handleESA() {
		// Start (or End) Of Selected Area is ignored
		
		// Description:
		//
		// Used by block-oriented terminals.
	}

	private void handleHTS() {
		// Character Tabulation Set is ignored
		
		// Description:
		// 
		// Causes a character tabulation stop to be set at the active position.
	}

	private void handleHTJ() {
		// Character Tabulation with Justification is ignored
		
		// Description:
		// 
		// Similar to Character Tabulation, except that instead of spaces 
		// or lines being placed after the preceding characters until the
		// next tab stop is reached, the spaces or lines are placed 
		// preceding the active field so that preceding graphic character 
		// is placed just before the next tab stop.
	}

	private void handleVTS() {
		// Line Tabulation Set is ignored
		
		// Description:
		// 
		// Causes a line tabulation stop to be set at the active position.
	}

	private void handlePLD() {
		handlePLU();
	}

	private void handlePLU() {
		// Partial Line Forward (or Backward) is ignored
		
		// Description:
		// 
		// Used to produce subscripts and superscripts in ISO/IEC 6429, 
		// e.g., in a printer. Subscripts use PLD text PLU while 
		// superscripts use PLU text PLD..
	}

	private void handleRI() {
		// Reverse Index is ignored
	}

	private void handleSS2() {
		handleSS3();
	}

	private void handleSS3() {
		// Single Shift 2/3 are ignored
		
		// Description:
		// 
		// Next character invokes a graphic character from the G2 or G3 
		// graphic sets respectively. In systems that conform to ISO/IEC
		// 4873 (ECMA-43), even if a C1 set other than the default is 
		// used, these two octets may only be used for this purpose.
	}

	private void handleDCS() {
		// Device Control String is ignored
		
		// Description:
		// 
		// Followed by a string of printable characters (0x20 through 0x7E)
		// and format effectors (0x08 through 0x0D), terminated by ST (0x9C).
	}

	private void handlePU1() {
		handlePU2();
	}

	private void handlePU2() {
		// Private Usage 1/2 is ignored
	}

	private void handleSTS() {
		// Set Transmit State is ignored
	}

	private void handleCCH() {
		// Cancel Character 
		
		// Description:
		// 
		// Destructive backspace, intended to eliminate ambiguity about meaning of BS.
		btr.deleteLastChar();
	}

	private void handleMW() {
		// Message Waiting is ignored
	}

	private void handleSPA() {
		handleEPA();
	}

	private void handleEPA() {
		// Start (or End) of Protected Area is ignored
		
		// Description:
		// 
		// Used by block-oriented terminals.
	}

	private void handleSOS() {
		// Start Of String is ignored
		
		// Description:
		// Followed by a control string terminated by ST (0x9C) that may 
		// contain any character except SOS or ST. Not part of the first 
		// edition of ISO/IEC 6429.
	}

	private void handleSGCI() {
		// Single Graphic Character Introducer is ignored
	}

	private void handleSCI() {
		// Single Character Introducer is ignored
		
		// Description:
		//
		// To be followed by a single printable character (0x20 through 0x7E)
		// or format effector (0x08 through 0x0D). The intent was to provide 
		// a means by which a control function or a graphic character that would
		// be available regardless of which graphic or control sets were in use 
		// could be defined. Definitions of what the following byte would invoke
		// was never implemented in an international standard. 
	}

	private void handleCSI() {
		// Control Sequence Introducer is ignored
		
		// Description: 
		//
		// Used to introduce control sequences that take parameters.
	}

	private void handleST() {
		// String Terminator is ignored
	}

	private void handleOSC() {
		handlePM();
	}

	private void handlePM() {
		handleAPC();
	}

	private void handleAPC() {
		// Operating System Command, Private Message and Application Program Command are ignored
		
		// Description:
		//
		// Followed by a string of printable characters (0x20 through 0x7E) 
		// and format effectors (0x08 through 0x0D), terminated by ST (0x9C). 
		// These three control codes were intended for use to allow in-band 
		// signaling of protocol information, but are rarely used for that 
		// purpose.
	}
}
