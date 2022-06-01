package shuan.test.testphotobase64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

public class ImgTypeConvert {

    /**
     * 将图片转换成Base64编码
     *
     * @param imgFilePath  待处理图片文件的地址
     * @return 注意这里只返回图片的base64编码，没有文件类型》》data:image/png;base64,添加类型信息后可以在img标签中显示
     */
    public static String getImgStr(String imgFilePath) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        File file=new File(imgFilePath);
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            if(data!=null&&data.length>0){
                //删除图片
//				file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Base64 base64 = new Base64();
        return base64.encodeAsString(data);
    }


    public static void main(String[] args) {
        String base64 = ImgTypeConvert.getImgStr("C:\\favicon.ico");
        System.out.println(base64);
        System.out.println(ImgTypeConvert.GenerateImage(base64, "C:\\a.jpg"));

    }

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param
     * @return
     */
    public static boolean GenerateImage(String imgStr,String imgFilePath)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        Base64 base64 = new Base64();

        try
        {
            //Base64解码
            byte[] b = base64.decode(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}

