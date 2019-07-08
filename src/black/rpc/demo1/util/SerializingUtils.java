package black.rpc.demo1.util;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 序列化工具
 *
 */
public class SerializingUtils {
	
	/**
	 * 序列化
	 * @param
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream out = null;
		try {
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out!=null)out.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}
	/**
	 * 反序列化
	 * @param
	 * @return
	 */
	public static Object deserialize(byte[] data) {
		ObjectInputStream in = null;
		Object obj = null;
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
			in = new ObjectInputStream(byteIn);
			obj = in.readObject();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(in!=null)in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
 
}
