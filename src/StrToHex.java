public class StrToHex {

    public static void main(String[] args) {
        String str = "Hello! This is test string.";
        char ch[] = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            sb.append(Integer.toHexString((int) ch[i]));
        }
        System.out.println("Original text: "+str);
        System.out.println("sb.toString: " + sb.toString());

        System.out.println("fromHexString: " + fromHexString(str));
        System.out.println("toHexString: " + toHexString(fromHexString(str)));

        String clearText = "testString For;0181;with.love";
        System.out.println("Clear Text  = " + clearText);

        char[] chars = clearText.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        String hexText = hex.toString();
        System.out.println("Hex Text  = " + hexText);
        String decodedText = HexToString(hexText);
        System.out.println("Decoded Text = "+decodedText);

        System.out.println("HexToString(sb.toString = "+HexToString(sb.toString()));


    }


    public static String HexToString(String hex){

        StringBuilder finalString = new StringBuilder();
        StringBuilder tempString = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            finalString.append((char)decimal);
            tempString.append(decimal);
        }
        System.out.println("tempString"+tempString);
        return finalString.toString();
    }

    // ********************************************************************************************************* //

    public static byte[] fromHexString(String s) {
        int length = s.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ((Character.digit(s.charAt(i * 2), 16) << 4) | Character
                    .digit(s.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

    public static String toHexString(byte[] fieldData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldData.length; i++) {
            int v = (fieldData[i] & 0xFF);
            if (v <= 0xF) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

}
