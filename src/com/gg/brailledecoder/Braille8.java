package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

public class Braille8 implements Braille {
	private Encoding encoding;
	private boolean[] p;
	private int value;
	
	public Braille8 () {
		encoding = Encoding.ISO;
	}
	
	public Braille8(Encoding type) {
		encoding = type;
	}
	/*
	 * Numbering of the braille points:
	 * 
	 *  |----|----|
	 *  | P1 | P4 |
	 *  |----|----|
	 *  | P2 | P5 |
	 *  |----|----|
	 *  | P3 | P6 |
	 *  |----|----|
	 *  | P7 | P8 |
	 *  |----|----|
	 * 
	 * Our keyboard:
	 *
	 *  left hand:	(vom kleinen Finger bis zum Zeigefinger)
	 *  P7 P3 P2 P1 
	 * 
	 *  right hand: (vom Zeigefinger bis zum kleinen Finger)
	 *  P4 P5 P6 P8
	 * 
	 * Internal Array:
	 *  p[0] = P1
	 *  p[1] = P2
	 *  p[2] = P3
	 *  p[3] = P4
	 *  p[4] = P5
	 *  p[5] = P6
	 *  p[6] = P7
	 *  p[7] = P8
	 * 
	 * Code:
	 *  Uses ISO 8859-1 or IBM-Codepage 437 or 850.
	 * 
	 *  table:
	 *  http://www.braille.ch/eb-id-hf.htm
	 * 
	 */
	
	public ReturnBraille getRecognizedSign(boolean[] p) {
		this.p = p;
		return braille();
	}
	
	public String getStringFromBraille(boolean[] p) {
		return Character.toString(getCharFromBraille(p));
	}
	
	public char getCharFromBraille(boolean[] p) {
		this.p = p;
		return braille().getSign().charAt(0);
	}
	
	public void setEncoding(Encoding type) {
		encoding = type;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getPressedButtons(boolean[] p) {
		StringBuilder ret = new StringBuilder("P");
		
		for(int i = 0; i < p.length; i++) 
			if(p[i]) 
				ret.append(Integer.toString((i+1)));
		
		return ret.toString();
	}
	
	private ReturnBraille braille() {
		value = getValue(p);
		
		switch(value) {
		case   0: return p_();
		case   1: return p8();
		case   2: return p7();
		case   3: return p78();
		case   4: return p6();
		case   5: return p68();
		case   6: return p67();
		case   7: return p678();
		case   8: return p5();
		case   9: return p58();
		case  10: return p57();
		case  11: return p578();
		case  12: return p56();
		case  13: return p568();
		case  14: return p567();
		case  15: return p5678();
		case  16: return p4();
		case  17: return p48();
		case  18: return p47();
		case  19: return p478();
		case  20: return p46();
		case  21: return p468();
		case  22: return p467();
		case  23: return p4678();
		case  24: return p45();
		case  25: return p458();
		case  26: return p457();
		case  27: return p4578();
		case  28: return p456();
		case  29: return p4568();
		case  30: return p4567();
		case  31: return p45678();
		case  32: return p3();
		case  33: return p38();
		case  34: return p37();
		case  35: return p378();
		case  36: return p36();
		case  37: return p368();
		case  38: return p367();
		case  39: return p3678();
		case  40: return p35();
		case  41: return p358();
		case  42: return p357();
		case  43: return p3578();
		case  44: return p356();
		case  45: return p3568();
		case  46: return p3567();
		case  47: return p35678();
		case  48: return p34();
		case  49: return p348();
		case  50: return p347();
		case  51: return p3478();
		case  52: return p346();
		case  53: return p3468();
		case  54: return p3467();
		case  55: return p34678();
		case  56: return p345();
		case  57: return p3458();
		case  58: return p3457();
		case  59: return p34578();
		case  60: return p3456();
		case  61: return p34568();
		case  62: return p34567();
		case  63: return p345678();
		case  64: return p2();
		case  65: return p28();
		case  66: return p27();
		case  67: return p278();
		case  68: return p26();
		case  69: return p268();
		case  70: return p267();
		case  71: return p2678();
		case  72: return p25();
		case  73: return p258();
		case  74: return p257();
		case  75: return p2578();
		case  76: return p256();
		case  77: return p2568();
		case  78: return p2567();
		case  79: return p25678();
		case  80: return p24();
		case  81: return p248();
		case  82: return p247();
		case  83: return p2478();
		case  84: return p246();
		case  85: return p2468();
		case  86: return p2467();
		case  87: return p24678();
		case  88: return p245();
		case  89: return p2458();
		case  90: return p2457();
		case  91: return p24578();
		case  92: return p2456();
		case  93: return p24568();
		case  94: return p24567();
		case  95: return p245678();
		case  96: return p23();
		case  97: return p238();
		case  98: return p237();
		case  99: return p2378();
		case 100: return p236();
		case 101: return p2368();
		case 102: return p2367();
		case 103: return p23678();
		case 104: return p235();
		case 105: return p2358();
		case 106: return p2357();
		case 107: return p23578();
		case 108: return p2356();
		case 109: return p23568();
		case 110: return p23567();
		case 111: return p235678();
		case 112: return p234();
		case 113: return p2348();
		case 114: return p2347();
		case 115: return p23478();
		case 116: return p2346();
		case 117: return p23468();
		case 118: return p23467();
		case 119: return p234678();
		case 120: return p2345();
		case 121: return p23458();
		case 122: return p23457();
		case 123: return p234578();
		case 124: return p23456();
		case 125: return p234568();
		case 126: return p234567();
		case 127: return p2345678();
		case 128: return p1();
		case 129: return p18();
		case 130: return p17();
		case 131: return p178();
		case 132: return p16();
		case 133: return p168();
		case 134: return p167();
		case 135: return p1678();
		case 136: return p15();
		case 137: return p158();
		case 138: return p157();
		case 139: return p1578();
		case 140: return p156();
		case 141: return p1568();
		case 142: return p1567();
		case 143: return p15678();
		case 144: return p14();
		case 145: return p148();
		case 146: return p147();
		case 147: return p1478();
		case 148: return p146();
		case 149: return p1468();
		case 150: return p1467();
		case 151: return p14678();
		case 152: return p145();
		case 153: return p1458();
		case 154: return p1457();
		case 155: return p14578();
		case 156: return p1456();
		case 157: return p14568();
		case 158: return p14567();
		case 159: return p145678();
		case 160: return p13();
		case 161: return p138();
		case 162: return p137();
		case 163: return p1378();
		case 164: return p136();
		case 165: return p1368();
		case 166: return p1367();
		case 167: return p13678();
		case 168: return p135();
		case 169: return p1358();
		case 170: return p1357();
		case 171: return p13578();
		case 172: return p1356();
		case 173: return p13568();
		case 174: return p13567();
		case 175: return p135678();
		case 176: return p134();
		case 177: return p1348();
		case 178: return p1347();
		case 179: return p13478();
		case 180: return p1346();
		case 181: return p13468();
		case 182: return p13467();
		case 183: return p134678();
		case 184: return p1345();
		case 185: return p13458();
		case 186: return p13457();
		case 187: return p134578();
		case 188: return p13456();
		case 189: return p134568();
		case 190: return p134567();
		case 191: return p1345678();
		case 192: return p12();
		case 193: return p128();
		case 194: return p127();
		case 195: return p1278();
		case 196: return p126();
		case 197: return p1268();
		case 198: return p1267();
		case 199: return p12678();
		case 200: return p125();
		case 201: return p1258();
		case 202: return p1257();
		case 203: return p12578();
		case 204: return p1256();
		case 205: return p12568();
		case 206: return p12567();
		case 207: return p125678();
		case 208: return p124();
		case 209: return p1248();
		case 210: return p1247();
		case 211: return p12478();
		case 212: return p1246();
		case 213: return p12468();
		case 214: return p12467();
		case 215: return p124678();
		case 216: return p1245();
		case 217: return p12458();
		case 218: return p12457();
		case 219: return p124578();
		case 220: return p12456();
		case 221: return p124568();
		case 222: return p124567();
		case 223: return p1245678();
		case 224: return p123();
		case 225: return p1238();
		case 226: return p1237();
		case 227: return p12378();
		case 228: return p1236();
		case 229: return p12368();
		case 230: return p12367();
		case 231: return p123678();
		case 232: return p1235();
		case 233: return p12358();
		case 234: return p12357();
		case 235: return p123578();
		case 236: return p12356();
		case 237: return p123568();
		case 238: return p123567();
		case 239: return p1235678();
		case 240: return p1234();
		case 241: return p12348();
		case 242: return p12347();
		case 243: return p123478();
		case 244: return p12346();
		case 245: return p123468();
		case 246: return p123467();
		case 247: return p1234678();
		case 248: return p12345();
		case 249: return p123458();
		case 250: return p123457();
		case 251: return p1234578();
		case 252: return p123456();
		case 253: return p1234568();
		case 254: return p1234567();
		case 255: return p12345678();}
		System.out.println("unknown sign");
		return new ReturnBraille("","");
	}
	
	private ReturnBraille p_() {
		return new ReturnBraille(" ", "Leerzeichen"); // P-
	}
	
	private ReturnBraille p8() {
		if(encoding == Encoding.ISO) return new ReturnBraille(" ", "Leerzeichen");
		return new ReturnBraille("╩", "T doppelt mit Fuß oben");
	}
	
	private ReturnBraille p7() {
		return new ReturnBraille(" ", "Leerzeichen"); 
	}
	
	private ReturnBraille p78() {
		// control 131 NBH
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char) 131), "NBH");
		return new ReturnBraille("░", "25 Prozent gefülltes Feld");
	}
	
	private ReturnBraille p6() {
		return new ReturnBraille("\'", "Apostroph");
	}
	
	private ReturnBraille p68() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╞", "T mit doppeltem Fuß rechts");
		return new ReturnBraille("¸", "Cedille");
	}
	
	private ReturnBraille p67() {
		// controlsign 156 ST
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)156), "ST");
		return new ReturnBraille("─", "Strich waagrecht");
	}
	
	private ReturnBraille p678() {
		// controlsign 138 VTS
		if(encoding == Encoding.ISO) return  new ReturnBraille(String.valueOf((char)138), "VTS");
		return  new ReturnBraille("┘", "Ecke unten rechts");
	}
	
	private ReturnBraille p5() {
		return new ReturnBraille("!", "Rufzeichen");
	}
	
	private ReturnBraille p58() {
		return new ReturnBraille("¢", "Cent Zeichen");
	}
	
	private ReturnBraille p57() {
		if(encoding == Encoding.CP437) return new ReturnBraille("\u2568", "T mit doppeltem Fuß oben");
		return new ReturnBraille("Ì", "I mit Gravis groß");
	}
	
	private ReturnBraille p578() {
		if(encoding == Encoding.CP437) return new ReturnBraille("∩", "Geschnitten mit");
		return new ReturnBraille("Ò", "O mit Gravis groß");
	}
	
	private ReturnBraille p56() {
		return new ReturnBraille(">", "Größer als");
	}
	
	private ReturnBraille p568() {
		if(encoding == Encoding.CP437) return new ReturnBraille("≤", "kleiner gleich");
		return new ReturnBraille("´", "Akut");
	}
	
	private ReturnBraille p567() {
		return new ReturnBraille("Ä", "Umlaut a groß");
	}
	
	private ReturnBraille p5678() {
		return new ReturnBraille("«", "doppeltes kleiner als");
	}
	
	private ReturnBraille p4() {
		return new ReturnBraille("\"", "Anführungszeichen"); // P4
	}
	
	private ReturnBraille p47() {
		return new ReturnBraille("Æ", "AE Doppellaut groß");
	}
	
	private ReturnBraille p48() {
		if(encoding == Encoding.CP437) return new ReturnBraille("∙", "gefüllter Ringel");
		return new ReturnBraille("¨", "Umlautzeichen");
	}
	
	private ReturnBraille p478() {
		return new ReturnBraille("æ", "AE Doppellaut klein");
	}
	
	private ReturnBraille p46() {
		return new ReturnBraille("$", "Dollar"); // P46
	}
	
	private ReturnBraille p468() {
		return new ReturnBraille("¥", "Yen");
	}
	
	private ReturnBraille p467() {
		return new ReturnBraille("£", "Pfund");
	}
	
	private ReturnBraille p4678() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╜", "Ecke unten rechts, senkrecht doppelt");
		return new ReturnBraille("¤", "Währungszeichen");
	}
	
	private ReturnBraille p45() {
		return new ReturnBraille(">", "Größer als");
	}
	
	private ReturnBraille p458() {
		if(encoding == Encoding.CP437) return new ReturnBraille("≥", "Größer gleich");
		return new ReturnBraille("¯", "Macron Überstrich");
	}
	
	private ReturnBraille p457() {
		// controlsign 128 PAD
		if(encoding == Encoding.ISO) return new ReturnBraille("€", "Euro Zeichen");
		else return new ReturnBraille("╣", "T doppelt mit Fuß links");
	}
	
	private ReturnBraille p4578() {
		return new ReturnBraille("»", "Doppeltes größer als");
	}
	
	private ReturnBraille p456() {
		// control sign 127 DEL
		return new ReturnBraille(String.valueOf((char)127), "DEL"); 
	}
	
	private ReturnBraille p4568() {
		return new ReturnBraille("°", "Grad Zeichen");
	}
	
	private ReturnBraille p4567() {
		return new ReturnBraille("_", "Underscore");
	}
	
	private ReturnBraille p45678() {
		// control US
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)31), "US");
		else return new ReturnBraille("▼", "Pfeilspitze unten");
	}
	
	private ReturnBraille p3() {
		return new ReturnBraille(".", "Punkt");
	}
	
	private ReturnBraille p38() {
		return new ReturnBraille("¿", "umgekehrtes Fragezeichen");
	}
	
	private ReturnBraille p37() {
		return new ReturnBraille("·", "Mittelpunkt");
	}
	
	private ReturnBraille p378() {
		// control 136 HTS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)136), "HTS");
		return new ReturnBraille("└", "Ecke unten links");
	}
	
	private ReturnBraille p36() {
		return new ReturnBraille("-", "Bindestrich");
	}
	
	private ReturnBraille p368() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╖", "Ecke oben rechts, senkrecht doppelt");
		return new ReturnBraille("-", "Silbentrennungsstrich");
	}
	
	private ReturnBraille p367() {
		return new ReturnBraille("¡", "Umgekehrtes Rufzeichen");
	}

	private ReturnBraille p3678() {
		// control BPH
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)130), "BPH");
		return new ReturnBraille("▒", "50 Prozent gefülltes Feld");
	}
	
	private ReturnBraille p35() {
		return new ReturnBraille("*", "Sternchen"); 
	}
	
	private ReturnBraille p358() {
		return new ReturnBraille("Ö", "Umlaut o groß");
	}
	
	private ReturnBraille p357() {
		if(encoding == Encoding.CP437) return new ReturnBraille("Σ", "Sigma groß");
		return new ReturnBraille("§", "Paragraph Zeichen");
	}
	
	private ReturnBraille p3578() {
		if(encoding == Encoding.CP437) return new ReturnBraille("≈", "ungefähr gleich");
		return new ReturnBraille("È", "E mit Gravis groß");
	}
	
	private ReturnBraille p356() {
		return new ReturnBraille(")", "Runde Klammer zu");
	}
	
	private ReturnBraille p3568() {
		if(encoding == Encoding.CP437) return new ReturnBraille("Ω", "Omega groß");
		return new ReturnBraille("Ý", "Y mit Akut groß");
	}
	
	private ReturnBraille p3567() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╪", "Kreuz waagrecht doppelt");
		return new ReturnBraille("Ð", "Eth groß (isländisch)");
	}
	
	private ReturnBraille p35678() {
		if(encoding == Encoding.CP437) return new ReturnBraille("▐", "Halbquader rechts");
		return new ReturnBraille("Ù", "U mit Gravis groß");
	}
	
	private ReturnBraille p34() {
		return new ReturnBraille("|", "Senkrechter Strich"); // P34
	}
	
	private ReturnBraille p348() {
		return new ReturnBraille("ì", "i mit Gravis klein");
	}
	
	private ReturnBraille p347() {
		return new ReturnBraille("\\", "Backslash");
	}
	
	private ReturnBraille p3478() {
		// control 28 FS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)28), "FS");
		return new ReturnBraille("∟", "Rechtwinkel");
	}
	
	private ReturnBraille p346() {
		return new ReturnBraille("0", "Null"); // P346
	}
	
	private ReturnBraille p3468() {
		return new ReturnBraille("ò", "o mit Gravis groß");
	}
	
	private ReturnBraille p3467() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╤", "T doppelt mit einfachem Fuß unten");
		else return new ReturnBraille("Ã", "A mit Tilde groß");
	}
	
	private ReturnBraille p34678() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╛", "Ecke unten rechts, waagrecht doppelt");
		return new ReturnBraille("ã", "a tilde klein");
	}
	
	private ReturnBraille p345() {
		return new ReturnBraille("`", "Gravis"); 
	}
	
	private ReturnBraille p3458() {
		return new ReturnBraille("ä", "Umlaut a klein");
	}
	
	private ReturnBraille p3457() {
		return new ReturnBraille("@", "Klammeraffe");
	}
	
	private ReturnBraille p34578() {
		// control NUL
		return new ReturnBraille(String.valueOf((char)0), "NUL");
	}
	
	private ReturnBraille p3456() {
		return new ReturnBraille("#", "Raute"); 
	}
	
	private ReturnBraille p34568() {
		return new ReturnBraille("ß", "Scharfes s");
	}
	
	private ReturnBraille p34567() {
		return new ReturnBraille("Å", "A mit Ringel groß");
	}
	
	private ReturnBraille p345678() {
		return new ReturnBraille("å", "a mit Ringel klein");
	}

	private ReturnBraille p2() {
		return new ReturnBraille(",", "Komma");
	}
	
	private ReturnBraille p28() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╥", "T mit doppeltem Fuss unten");
		return new ReturnBraille("Á", "A mit Akut groß");
	}
	
	private ReturnBraille p27() {
		// control PLD
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)139), "PLD"); 
		return new ReturnBraille("┴", "T mit Fuß oben");
	}
	
	private ReturnBraille p278() {
		// control 149 MN
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)149), "MN"); 
		return new ReturnBraille("┐", "Ecke oben rechts");
	}
	
	private ReturnBraille p26() {
		return new ReturnBraille("?", "Fragezeichen"); // P26
	}
	
	private ReturnBraille p268() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╡", "T mit doppeltem Fuß links");
		return new ReturnBraille("Ú", "U mit Akut groß");
	}
	
	private ReturnBraille p267() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╟", "T doppelt mit einfachem Fuß links");
		else return new ReturnBraille("Õ", "O mit Tilde groß");
	}
	
	private ReturnBraille p2678() {
		// control SCI
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)154), "SCI");
		return new ReturnBraille("╝", "Ecke doppelt unten rechts");
	}
	
	private ReturnBraille p25() {
		return new ReturnBraille(":", "Doppelpunkt");
	}
	
	private ReturnBraille p258() {
		if(encoding == Encoding.CP437) return new ReturnBraille("Γ", "Gamma groß");
		return new ReturnBraille("Ì", "i Akut groß");
	}
	
	private ReturnBraille p257() {
		// controlsign 141 RI
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)141), "RI");
		else return new ReturnBraille("┌", "Ecke oben links");
	}
	
	private ReturnBraille p2578() {
		// control SOS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)152), "SOS"); 
		else return new ReturnBraille("═", "gleich");
	}
	
	private ReturnBraille p256() {
		return new ReturnBraille("/", "Schrägstrich");
	}
	
	private ReturnBraille p2568() {
		if(encoding == Encoding.CP437) return new ReturnBraille("Θ", "Theta groß");
		return new ReturnBraille("Ó", "o mit Akut groß");
	}
	
	private ReturnBraille p2567() {
		return new ReturnBraille("Ñ", "n mit Tilde groß");
	}
	
	private ReturnBraille p25678() {
		return new ReturnBraille("¬", "nicht");
	}
	
	private ReturnBraille p24() {
		return new ReturnBraille("i");
	}
	
	private ReturnBraille p248() {
		// controlsign 137 HTJ
		switch(encoding) {
		case ISO: return new ReturnBraille(String.valueOf((char)137), "HTJ");
		case CP437: return new ReturnBraille("╘", "Ecke unten links, waagrecht doppelt");
		case CP850: return new ReturnBraille("ı", "i klein ohne Punkt (türkisch)");
		default: return new ReturnBraille("", "");
		}
	}
	
	private ReturnBraille p247() {
		return new ReturnBraille("I", "i groß");
	}
	
	private ReturnBraille p2478() {
		// control 9 TAB
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)9), "TAB"); 
		else return new ReturnBraille("○", "Kreis");
	}
	
	private ReturnBraille p246() {
		return new ReturnBraille("9", "Neun");
	}
	
	private ReturnBraille p2468() {
		return new ReturnBraille("ö", "Umlaut o");
	}
	
	private ReturnBraille p2467() {
		if(encoding == Encoding.CP437) return new ReturnBraille("∞", "unendlich");
		else return new ReturnBraille("Ø", "o Querstrich groß");
	}
	
	private ReturnBraille p24678() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╙", "Ecke unten links, senkrecht doppelt");
		return new ReturnBraille("ø", "o Querstrich");
	}
	
	private ReturnBraille p245() {
		return new ReturnBraille("j"); 
	}
	
	private ReturnBraille p2458() {
		return new ReturnBraille("º", "o unterstrich");
	}
	
	private ReturnBraille p2457() {
		return new ReturnBraille("J", "j groß");
	}
	
	private ReturnBraille p24578() {
		// control 10 LF
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)10), "LF"); 
		return new ReturnBraille("◙", "Kreis invers");
	}
	
	private ReturnBraille p2456() {
		return new ReturnBraille("w");
	}
	
	private ReturnBraille p24568() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╢", "T doppelt mit einfachem Fuß links");
		return new ReturnBraille("ý", "y mit Akut klein");
	}
	
	private ReturnBraille p24567() {
		return new ReturnBraille("W", "w groß");
	}
	
	private ReturnBraille p245678() {
		// control 23 ETB
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)23), "ETB"); 
		return new ReturnBraille("↨", "senkrechter Doppelpfeil unterstrichen");
	}
	
	private ReturnBraille p23() {
		return new ReturnBraille(";", "Semikolon"); 
	}
	
	private ReturnBraille p238() {
		// control 133 NEL
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)133), "NEL");
		return new ReturnBraille("╦", "T doppelt mit Fuß unten");
	}
	
	private ReturnBraille p237() {
		// controlsign 140 PLU
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)140), "PLU");
		else return new ReturnBraille("|", "Strich senkrecht");
	}
	
	private ReturnBraille p2378() {
		// control 150 SPA
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)150), "SPA");
		else return new ReturnBraille("╚", "doppelte Ecke unten links");
	}
	
	private ReturnBraille p236() {
		return new ReturnBraille("(", "Runde Klammer auf");
	}
	
	private ReturnBraille p2368() {
		return new ReturnBraille("Ü", "Umlaut u groß");
	}
	
	private ReturnBraille p2367() {
		// control 146 PU2
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)146), "PU2");
		else return new ReturnBraille("├", "T mit Fuß links");
	}
	
	private ReturnBraille p23678() {
		if(encoding == Encoding.CP437) return new ReturnBraille("▌", "Halbquader links");
		return new ReturnBraille("À", "A mit Gravis groß");
	}
	
	private ReturnBraille p235() {
		return new ReturnBraille("+", "Plus"); // P235
	}
	
	private ReturnBraille p2358() {
		if(encoding == Encoding.CP437) return new ReturnBraille("Φ", "Phi groß");
		return new ReturnBraille("Ë", "Umlaut e groß");
	}
	
	private ReturnBraille p2357() {
		if(encoding == Encoding.CP437) return new ReturnBraille("₧", "Peseta");
		return new ReturnBraille("Þ", "Thorn groß (isländisch)");
	}
	
	private ReturnBraille p23578() {
		return new ReturnBraille("±", "plus oder minus");
	}
	
	private ReturnBraille p2356() {
		return new ReturnBraille("=", "gleich");
	}
	
	private ReturnBraille p23568() {
		if(encoding == Encoding.CP437) return new ReturnBraille("≡", "identisch gleich");
		return new ReturnBraille("Ï", "Umlaut i groß");
	}
	
	private ReturnBraille p23567() {
		// control 145 PU1
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)145), "PU1"); 
		return new ReturnBraille("╔", "Ecke doppelt oben links");
	}
	
	private ReturnBraille p235678() {
		// control 158 PM
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)158), "PM"); 
		return new ReturnBraille("╔", "Ecke doppelt oben rechts");
	}
	
	private ReturnBraille p234() {
		return new ReturnBraille("s"); // P234
	}
	
	private ReturnBraille p2348() {
		if(encoding == Encoding.CP437) return new ReturnBraille("σ", "Sigma klein");
		return new ReturnBraille("×", "Multiplikationszeichen");
	}
	
	private ReturnBraille p2347() {
		return new ReturnBraille("S", "großes S");
	}
	
	private ReturnBraille p23478() {
		// control sign \19 DC3
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)19), "DC3");
		return new ReturnBraille("‼", "doppeltes Rufzeichen");
	}
	
	private ReturnBraille p2346() {
		return new ReturnBraille("~", "Tilde");
	}
	
	private ReturnBraille p23468() {
		return new ReturnBraille("è", "e mit Gravis klein");
	}
	
	private ReturnBraille p23467() {
		return new ReturnBraille("^", "Zirkumflex");
	}
	
	private ReturnBraille p234678() {
		// control 30 RS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)30), "RS");
		return new ReturnBraille("▲", "Pfeilspitze oben");
	}
	
	private ReturnBraille p2345() {
		return new ReturnBraille("t");
	}
	
	private ReturnBraille p23458() {
		if(encoding == Encoding.CP437) return new ReturnBraille("τ", "Tau klein");
		return new ReturnBraille("ð", "eth klein (isländisch)");
	}
	
	private ReturnBraille p23457() {
		return new ReturnBraille("T", "großes T");
	}
	
	private ReturnBraille p234578() {
		// control 20 DC4
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)20), "DC4");
		return new ReturnBraille("¶", "Pilcrow Zeichen");
	}
	
	private ReturnBraille p23456() {
		return new ReturnBraille("}", "geschweifte Klammer zu");
	}
	
	private ReturnBraille p234568() {
		return new ReturnBraille("ù", "u mit Akut klein");
	}
	
	private ReturnBraille p234567() {
		return new ReturnBraille("]", "Eckige Klammer zu");
	}
	
	private ReturnBraille p2345678() {
		// control 29 GS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)29), "GS");
		return new ReturnBraille("↔", "Doppelpfeil waagerecht");
	}
	
	private ReturnBraille p1() {
		return new ReturnBraille("a");
	}
	
	private ReturnBraille p18() {
		if(encoding == Encoding.CP437) return new ReturnBraille("α", "alpha klein");
		else return new ReturnBraille("¹", "Exponent Eins");
	}
	
	private ReturnBraille p17() {
		return new ReturnBraille("A", "großes A");
	}
	
	private ReturnBraille p178() {
		// control char 1 SOH
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)1), "SOH");
		return new ReturnBraille("☺", "Gesicht");
	}
	
	private ReturnBraille p16() {
		return new ReturnBraille("1", "eins");
	}
	
	private ReturnBraille p168() {
		return new ReturnBraille("á", "a mit Akut");
	}
	
	private ReturnBraille p167() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╧", "T doppelt mit einfachem Fuß oben");
		else return new ReturnBraille("Â", "a zirkonflex groß");
	}
	
	private ReturnBraille p1678() {
		return new ReturnBraille("â", "a zirkonflex");
	}

	private ReturnBraille p15() {
		return new ReturnBraille("e");
	}
	
	private ReturnBraille p158() {
		if(encoding == Encoding.CP437) return new ReturnBraille("ε", "epsilon klein");
		return new ReturnBraille("¦", "unterbrochener senkrechter Strich");
	}
	
	private ReturnBraille p157() {
		return new ReturnBraille("E", "großes E");
	}
	
	private ReturnBraille p1578() {
		// control 5 ENQ
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)5), "ENQ");
		return new ReturnBraille("♣", "Treff");
	}
	
	private ReturnBraille p156() {
		return new ReturnBraille("5", "fünf");
	}
	
	private ReturnBraille p1568() {
		return new ReturnBraille("ú", "u mit Akut klein");
	}
	
	private ReturnBraille p1567() {
		if(encoding == Encoding.CP437) return new ReturnBraille("⌡", "Integral unterer Teil");
		else return new ReturnBraille("Û", "u zirkonflex groß");
	}
	
	private ReturnBraille p15678() {
		return new ReturnBraille("û", "u zirkonflex");
	}
	
	private ReturnBraille p14() {
		return new ReturnBraille("c");
	}
	
	private ReturnBraille p148() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╕", "Ecke oben rechts, waagrecht doppelt");
		return new ReturnBraille("³", "Exponent drei");
	}
	
	private ReturnBraille p147() {
		return new ReturnBraille("C", "großes c");
	}
	
	private ReturnBraille p1478() {
		// control 3 ETX
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)3), "ETX");
		return new ReturnBraille("♥", "Herz");
	}
	
	private ReturnBraille p146() {
		return new ReturnBraille("3", "drei");
	}
	
	private ReturnBraille p1468() {
		return new ReturnBraille("í", "i mit Akut");
	}
	
	private ReturnBraille p1467() {
		if(encoding == Encoding.CP437) return new ReturnBraille("√", "Wurzel");
		else return new ReturnBraille("Î", "i zirkonflex groß");
	}
	
	private ReturnBraille p14678() {
		return new ReturnBraille("î", "i zirkonflex");
	}
	
	private ReturnBraille p145() {
		return new ReturnBraille("d");
	}
	
	private ReturnBraille p1458() {
		if(encoding == Encoding.CP437) return new ReturnBraille("δ", "delta");
		return new ReturnBraille("¶", "pilcrow Zeichen");
	}
	
	private ReturnBraille p1457() {
		return new ReturnBraille("D", "großes D");
	}
	
	private ReturnBraille p14578() {
		// control 4 EOT
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)4), "EOT");
		return new ReturnBraille("♦", "Karo");
	}
	
	private ReturnBraille p1456() {
		return new ReturnBraille("4", "vier");
	}
	
	private ReturnBraille p14568() {
		return new ReturnBraille("ó", "o mit Akut");
	}
	
	private ReturnBraille p14567() {
		if(encoding == Encoding.CP437) return new ReturnBraille("⌐", "nicht");
		return new ReturnBraille("Ô", "o zirkonflex groß");
	}
	
	private ReturnBraille p145678() {
		return new ReturnBraille("ô", "o zirkonflex");
	}

	private ReturnBraille p13() {
		return new ReturnBraille("k");
	}
	private ReturnBraille p138() {
		// control 147 STS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)147), "STS");
		return new ReturnBraille("┬", "T mit Fuß unten");
	}
	
	private ReturnBraille p137() {
		return new ReturnBraille("K", "großes k");
	}
	
	private ReturnBraille p1378() {
		// control 11 VT
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)11), "VT");
		return new ReturnBraille("♂", "männlich");
	}
	
	
	private ReturnBraille p136() {
		return new ReturnBraille("u");
	}
	
	private ReturnBraille p1368() {
		return new ReturnBraille("¼", "ein viertel");
	}
	
	private ReturnBraille p1367() {
		return new ReturnBraille("U", "großes U");
	}
	
	private ReturnBraille p13678() {
		// control 21 NAK
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)21), "NAK");
		return new ReturnBraille("§", "Paragraph");
	}
	
	private ReturnBraille p135() {
		return new ReturnBraille("o");
	}
	
	private ReturnBraille p1358() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╓", "Ecke oben links, senkrecht doppelt");
		return new ReturnBraille("õ", "o mit Tilde");
	}
	
	private ReturnBraille p1357() {
		return new ReturnBraille("O", "großes o");
	}
	
	private ReturnBraille p13578() {
		// control 15 SI
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)15), "SI");
		return new ReturnBraille("☼", "Sonne");
	}
	
	private ReturnBraille p1356() {
		return new ReturnBraille("z");
	}
	
	private ReturnBraille p13568() {
		// control 151 EPA
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)151), "EPA");
		return new ReturnBraille("┤", "T mit Fuß links");
	}
	
	private ReturnBraille p13567() {
		return new ReturnBraille("Z", "großes Z");
	}
	
	private ReturnBraille p135678() {
		// control 26 SUB
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)26), "SUB");
		return new ReturnBraille("→", "Pfeil nach rechts");
	}
	
	private ReturnBraille p134() {
		return new ReturnBraille("m");
	}
	
	private ReturnBraille p1348() {
		return new ReturnBraille("µ", "Mikro");
	}
	
	private ReturnBraille p1347() {
		return new ReturnBraille("M", "großes M");
	}
	
	private ReturnBraille p13478() {
		// control \13 FS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)13), "FS");
		return new ReturnBraille("♪", "Achtelnote");
	}
	
	private ReturnBraille p1346() {
		return new ReturnBraille("x"); 
	}
	
	private ReturnBraille p13468() {
		if(encoding == Encoding.CP437) return new ReturnBraille("ⁿ", "Exponent n");
		return new ReturnBraille("¾", "drei viertel");
	}
	
	private ReturnBraille p13467() {
		return new ReturnBraille("X", "großes X");
	}
	
	private ReturnBraille p134678() {
		// control 24 CAN
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)24), "CAN");
		return new ReturnBraille("↑", "Pfeil nach oben");
	}
	
	private ReturnBraille p1345() {
		return new ReturnBraille("n");
	}
	
	private ReturnBraille p13458() {
		return new ReturnBraille("ñ", "n mit Tilde");
	}
	
	private ReturnBraille p13457() {
		return new ReturnBraille("N", "großes n");
	}
	
	private ReturnBraille p134578() {
		// control 14 SO
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)14), "SO");
		return new ReturnBraille("♫", "Sechzehntelnote");
	}
	
	private ReturnBraille p13456() {
		return new ReturnBraille("y");
	}
	
	private ReturnBraille p134568() {
		return new ReturnBraille("ÿ", "umlaut y");
	}
	
	private ReturnBraille p134567() {
		return new ReturnBraille("Y", "großes y");
	}
	
	private ReturnBraille p1345678() {
		// control 25 EM
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)25), "EM");
		return new ReturnBraille("↓", "Pfeil nach unten");
	}

	private ReturnBraille p12() {
		return new ReturnBraille("b");
	}
	
	private ReturnBraille p128() {
		return new ReturnBraille("²", "Exponent 2"); 
	}
	private ReturnBraille p127() {
		return new ReturnBraille("B", "großes B");
	}
	private ReturnBraille p1278() {
		// control 2 STX
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)2), "STX");
		return new ReturnBraille("☻", "Gesicht invers");
	}

	private ReturnBraille p126() {
		return new ReturnBraille("2", "zwei");
	}
	
	private ReturnBraille p1268() {
		// control 132 IND
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)132), "IND");
		return new ReturnBraille("║", "doppelter senkrechter Strich");
	}
	
	private ReturnBraille p1267() {
		if(encoding == Encoding.CP437) return new ReturnBraille("⌠", "Integral oberer Teil");
		return new ReturnBraille("Ê", "e zirkonflex groß");
	}
	
	private ReturnBraille p12678() {
		return new ReturnBraille("ê", "e zirkonflex");
	}
	
	private ReturnBraille p125() {
		return new ReturnBraille("h");
	}
	
	private ReturnBraille p1258() {
		return new ReturnBraille("ª", "a unterstrich");
	}
	
	private ReturnBraille p1257() {
		return new ReturnBraille("H", "großes H");
	}
	
	private ReturnBraille p12578() {
		// control 8 BS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)8), "BS");
		return new ReturnBraille("◘", "großer Punkt invers");
	}
	
	private ReturnBraille p1256() {
		return new ReturnBraille("8", "Acht");
	}
	
	private ReturnBraille p12568() {
		return new ReturnBraille("ü", "Umlaut u");
	}
	
	private ReturnBraille p12567() {
		// control 142 SS2
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)142), "SS2");
		else return new ReturnBraille("▄", "Halbquader unten");
	}
	
	private ReturnBraille p125678() {
		return new ReturnBraille("÷", "Divisionszeichen");
	}
	
	private ReturnBraille p124() {
		return new ReturnBraille("f");
	}
	
	private ReturnBraille p1248() {
		// control 134 SSA
		switch(encoding) {
		case CP437: return new ReturnBraille("φ", "leere Menge");
		case CP850: return new ReturnBraille("‗", "Doppelunterstreichung");
		default: return new ReturnBraille(String.valueOf((char)134), "SSA");
		}
	}
	
	private ReturnBraille p1247() {
		return new ReturnBraille("F", "großes F");
	}
	
	private ReturnBraille p12478() {
		// control 6 ACK
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)6), "ACK");
		return new ReturnBraille("♠", "Pik");
	}
	
	private ReturnBraille p1246() {
		return new ReturnBraille("6", "Sechs");
	}
	
	private ReturnBraille p12468() {
		return new ReturnBraille("ë", "Umlaut e");
	}
	
	private ReturnBraille p12467() {
		// control 143 SS3
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)143), "SS3");
		else return new ReturnBraille("┼", "Kreuz");
	}
	
	private ReturnBraille p124678() {
		// control 144 DCS
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)144), "DCS");
		else return new ReturnBraille("ƒ", "florin");
	}
	
	private ReturnBraille p1245() {
		return new ReturnBraille("g");
	}
	
	private ReturnBraille p12458() {
		// control 135 ESA
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)135), "ESA");
		return new ReturnBraille("╗", "Ecke doppelt rechts oben");
	}
	
	private ReturnBraille p12457() {
		return new ReturnBraille("G", "großes G");
	}
	
	private ReturnBraille p124578() {
		// control 7 BEL
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)7), "BEL");
		return new ReturnBraille("•", "großer Punkt");
	}
	
	private ReturnBraille p12456() {
		return new ReturnBraille("7", "Sieben");
	}
	
	private ReturnBraille p124568() {
		return new ReturnBraille("ï", "Umlaut i");
	}
	
	private ReturnBraille p124567() {
		// control 157 OSC
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)157), "OSC");
		return new ReturnBraille("▀", "Halbquader oben");
	}
	
	private ReturnBraille p1245678() {
		// control 153 SGCI
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)153), "SGCI");
		return new ReturnBraille("╬", "Kreuz doppelt");
	}
	
	private ReturnBraille p123() {
		return new ReturnBraille("l");
	}
	
	private ReturnBraille p1238() {
		// control 148 CCH
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)148), "CCH");
		return new ReturnBraille("╠", "T doppelt mit Fuß links");
	}
	
	private ReturnBraille p1237() {
		return new ReturnBraille("L", "großes L");
	}
	
	private ReturnBraille p12378() {
		// control 12 FF
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)12), "FF");
		return new ReturnBraille("♀", "weiblich");
	}
	
	private ReturnBraille p1236() {
		return new ReturnBraille("v");
	}
	
	private ReturnBraille p12368() {
		return new ReturnBraille("½", "ein halb");
	}
	
	private ReturnBraille p12367() {
		return new ReturnBraille("V", "großes V");
	}
	
	private ReturnBraille p123678() {
		// control 22 SYN
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)22), "SYN");
		return new ReturnBraille("▬", "Quader klein waagrecht");
	}
	
	private ReturnBraille p1235() {
		return new ReturnBraille("r");
	}
	
	private ReturnBraille p12358() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╫", "Kreuz senkrecht doppelt");
		return new ReturnBraille("®", "registered Trademark");
	}
	
	private ReturnBraille p12357() {
		return new ReturnBraille("R", "großes R");
	}
	
	private ReturnBraille p123578() {
		// control 18 DC2
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)18), "DC2");
		return new ReturnBraille("↕", "Doppelpfeil senkrecht");
	}
	
	private ReturnBraille p12356() {
		return new ReturnBraille("{", "geschweifte Klammer auf");
	}
	
	private ReturnBraille p123568() {
		return new ReturnBraille("à", "a mit Gravis");
	}
	
	private ReturnBraille p123567() {
		return new ReturnBraille("[", "Eckige Klammer auf");
	}
	
	private ReturnBraille p1235678() {
		// control 27 ESC
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)27), "ESC");
		return new ReturnBraille("←", "Pfeil nach links");
	}
	
	private ReturnBraille p1234() {
		return new ReturnBraille("p"); 
	}
	
	private ReturnBraille p12348() {
		if(encoding == Encoding.CP437) return new ReturnBraille("τ", "Pi klein");
		return new ReturnBraille("þ", "thorn klein (isländisch)");
	}
	
	private ReturnBraille p12347() {
		return new ReturnBraille("P", "großes P");
	}
	
	private ReturnBraille p123478() {
		// control 16 DLE
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)16), "DLE");
		return new ReturnBraille("►", "Pfeilspitze rechts");
	}
	
	private ReturnBraille p12346() {
		return new ReturnBraille("&", "Kaufmannsund"); 
	}
	
	private ReturnBraille p123468() {
		if(encoding == Encoding.CP437) return new ReturnBraille("╒", "Ecke oben links, waagrecht doppelt");
		return new ReturnBraille("©", "Copyright");
	}
	
	private ReturnBraille p123467() {
		return new ReturnBraille("Ç", "C mit Cedille groß");
	}
	
	private ReturnBraille p1234678() {
		return new ReturnBraille("ç", "c mit Cedille");
	}
	
	private ReturnBraille p12345() {
		return new ReturnBraille("q");
	}
	
	private ReturnBraille p123458() {
		// control 145 PU1
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)145), "PU1");
		return new ReturnBraille("■", "Mittequadrat");
	}
	
	private ReturnBraille p123457() {
		return new ReturnBraille("Q", "großes Q");
	}
	
	private ReturnBraille p1234578() {
		// control 17 DC1
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)17), "DC1");
		return new ReturnBraille("◄", "Pfeilspitze links");
	}
	
	private ReturnBraille p123456() {
		return new ReturnBraille("%", "Prozent");
	}
	
	private ReturnBraille p1234568() {
		return new ReturnBraille("é", "e mit Akut");
	}
	
	private ReturnBraille p1234567() {
		return new ReturnBraille("É", "e mit Akut groß");
	}
	
	private ReturnBraille p12345678() {
		// control 159 APC
		if(encoding == Encoding.ISO) return new ReturnBraille(String.valueOf((char)159), "APC");
		return new ReturnBraille("█", "hundert Prozent gefülltes Feld");
	}

	private int getValue(boolean[] p) {
		int ret = 0;
		
		for(int i = 0; i < 8; i++) {
			ret = ret << 1;
			if(p[i]) ret++;
		}
		
		return ret;
	}
}
