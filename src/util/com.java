/*
 * jSerialComm Library �ʿ�
 * ��ġ�� ���� ��ũ : https://mainia.tistory.com/2273
 * jSerialComm������ ������Ʈ ���ο� �־�� �մϴ�.
 */
package util;

import com.fazecast.jSerialComm.*;
import java.util.*;
import java.io.*;
import java.nio.ByteBuffer;

public class com {
	public static SerialPort userPort;
	static InputStream in;

	public static double checkTemp() {
		byte[] output = new byte[13];
		String Response, HiSkinTmp, LoSkinTmp, HiSensorTmp, LoSensorTmp;
		int iHiSkinTmp, iLoSkinTmp, iHiSensorTmp, iLoSensorTmp;
		int cnt = 0;
		double temp = -100;

		// Request Byte
		ByteBuffer objByteBuffer = ByteBuffer.allocate(8);
		objByteBuffer.put((byte) 0x01); // Request#1
		objByteBuffer.put((byte) 0x03); // Request#2
		objByteBuffer.put((byte) 0x00); // Request#3
		objByteBuffer.put((byte) 0x01); // Request#4
		objByteBuffer.put((byte) 0x00); // Request#5
		objByteBuffer.put((byte) 0x04); // Request#6
		objByteBuffer.put((byte) 0x15); // Request#7 CRC Lower
		objByteBuffer.put((byte) 0xC9); // Request#8 CRC Upper
		// ������Ʈ ���� �������̶� CRC ���� ����
		byte[] arrBuffBytes = objByteBuffer.array();

		/*
		 * This returns an array of commport addresses, not useful for the client but
		 * useful for iterating through to get an actual list of com parts available
		 */
		SerialPort ports[] = SerialPort.getCommPorts();
		int i = 1;

		System.out.println("\nCOM Ports available on machine");

		SerialPort userPort = SerialPort.getCommPort("COM3"); // Set Port

		/*
		 * Initializing port & Set parameters bps = 19,200 DATA bit = 8 Stop bit = 1
		 * Parity = none
		 */
		userPort.setComPortParameters(19200, 8, 1, 0);
		userPort.openPort(100); // �۽� �ð� ����
		System.out.println("Port initialized!");

		while (cnt == 0) { // Loop for data

			if (userPort.isOpen()) { // Port Open

				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}

				userPort.writeBytes(arrBuffBytes, 8); // send Request Message (Master->Sensor)

			} else {
				System.out.println("Port not available");
				return temp;
			}

			/*
			 * Read data Response = 13 byte response HiSkinTmp = Data Value 2 Hi LoSkinTmp =
			 * Data Value 2 Lo HiSensorTmp = Data Value 4 Hi LoSensorTmp = Data Value 4 Lo
			 */

			userPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			InputStream in = userPort.getInputStream(); // buffer for input data

			try {
				for (int j = 0; j < 13; ++j) {
					output[j] = (byte) in.read(); // read input data (Sensor->Master)
				}
				StringBuffer sb = new StringBuffer(output.length * 2);
				String hexaDecimal;

				// Hex byte[] to Hex String
				for (int x = 0; x < output.length; x++) {
					hexaDecimal = "0" + Integer.toHexString(0xff & output[x]);
					sb.append(hexaDecimal.substring(hexaDecimal.length() - 2));
				}

				Response = sb.toString();
				/*
				 * Hex String to Integer
				 */
				// Response [10-13]th byte
				HiSkinTmp = Response.substring(10, 12);
				iHiSkinTmp = Integer.parseInt(HiSkinTmp, 16);
				LoSkinTmp = Response.substring(12, 14);
				iLoSkinTmp = Integer.parseInt(LoSkinTmp, 16);
				// Response [18-21]th byte
				HiSensorTmp = Response.substring(18, 20);
				iHiSensorTmp = Integer.parseInt(HiSensorTmp, 16);
				LoSensorTmp = Response.substring(20, 22);
				iLoSensorTmp = Integer.parseInt(LoSensorTmp, 16);

				cnt++;
				System.out.println("HiSkinTmp : " + iHiSkinTmp);
				System.out.println("LoSkinTmp : " + iLoSkinTmp);
				System.out.println("HiSensorTmp : " + iHiSensorTmp);
				System.out.println("LoSensorTmp : " + iLoSensorTmp);
				System.out.println("----------------------" + cnt + "----------------------");

				// Calculate Temperature
				short uTemp1 = (short) (output[5] * 256 + output[6]);
				double dTemp1 = (double) uTemp1 * 0.02 - 273.15;
				short uTemp2 = (short) (output[9] * 256 + output[10]);
				double dTemp2 = (double) uTemp2 * 0.02 - 273.15;
				temp = dTemp1;
				System.out.println("SkinTemp : " + dTemp1);
				System.out.println("SensorTemp : " + dTemp2);
				System.out.println("=======================================================================");

				in.close();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

		}
		userPort.closePort();

		return Math.round(temp * 100) / 100.0;
	}
}