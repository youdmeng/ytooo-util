package ml.ytooo.file;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChengZhenxing on 2017/3/20.
 */
public class FileUtil {
    public static final String lineSeparator = "\r\n";

    public static List<List<String>> readFileByLine(InputStream inputStream, String regEx) throws IOException {
        List<List<String>> fileStrList = new ArrayList<>();
        if (null != inputStream) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String temp = null;
                temp = reader.readLine();
                while (temp != null) {
                    String[] lineStr = temp.split(StringEscapeUtils.unescapeJava(regEx), -1);
                    List<String> lineList = Arrays.asList(lineStr);
                    fileStrList.add(lineList);
                    temp = reader.readLine();
                }
            }
        }
        return fileStrList;
    }

    public static File generateFile(String path, List<List<String>> content, String regEx) throws IOException {
        UUID uuid = UUID.randomUUID();
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(path + File.separator + uuid.toString() + ".txt");
        if (file.createNewFile()) {
            writeFileContent(file, content, StringEscapeUtils.unescapeJava(regEx));
            return file;
        } else {
            throw new IOException("创建文件失败");
        }
    }

    private static void writeFileContent(File file, List<List<String>> content, String regEx) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file); PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            String fileContent = getContentString(content, StringEscapeUtils.unescapeJava(regEx));
            printWriter.write(fileContent.toCharArray());
            printWriter.flush();
        }
    }

    public static String getContentString(List<List<String>> content, String regEx) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            for (int j = 0; j < content.get(i).size(); j++) {
                builder.append(content.get(i).get(j));
                if (j < content.get(i).size() - 1) {
                    builder.append(StringEscapeUtils.unescapeJava(regEx));
                }
            }
            builder.append(lineSeparator);
        }
        return builder.toString();
    }



    /**
     * @since 把2003版的word即doc文件转为html文件
     * @author wanglishuai
     * @date 2018/11/12 11:09
     * @param file 数据库中的resume文件路径
     * @return html的路径
     * @throws IOException, TransformerException, ParserConfigurationException
     */
    public static String docToHtml(String file) throws IOException, TransformerException, ParserConfigurationException {
        //线上库
        String library ="/mnt/tomcat/apache-tomcat/webapps/";
        //测试库
//        String library = "/usr/local/tomcat/webapps/";
        //doc文件的名字
        final String fileName =file.substring(file.lastIndexOf("/")+1,file.length());
        //doc文件的物理路径
        String filepath = library+"erp/erp_img/";
        //前端访问时用的html和图片的路径
        String htmlPath = "erp/erp_img/"+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //html的物理路径
        String htmlReallyPath =filepath+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //如果文件中有图片则图片的路径
        final String imagepath = htmlReallyPath+"image/";
        //转换后html文件的名字
        String htmlName = fileName.substring(0,fileName.lastIndexOf("."))+".html";

        /*开发时路径
        //doc文件的名字
        final String fileName =file.substring(file.lastIndexOf("/")+1,file.length());
        //doc文件的物理路径
        String filepath = "..\\mnt\\tomcat\\apache-tomcat\\webapps\\erp\\erp_img\\";
        //前端访问时用的html的路径
//        String htmlPath ="..\\erp_img\\"+fileName.substring(0,fileName.lastIndexOf("."))+"\\";
        String htmlPath = "html\\recruitManage\\resume\\"+fileName.substring(0,fileName.lastIndexOf("."))+"\\";
        //html的物理路径
        String htmlReallyPath = "D:\\ERP_angulajs\\"+htmlPath;
        //如果文件中有图片则图片的路径
        final String imagepath = htmlReallyPath+"image\\";
        //转换后html文件的名字
        String htmlName = fileName.substring(0,fileName.lastIndexOf("."))+".html";*/

        InputStream input = new FileInputStream(new File(filepath + fileName));
        HWPFDocument wordDocument = new HWPFDocument(input);
        File imgPath = new File(imagepath);
        if(!imgPath.exists()){//图片目录不存在则创建
            imgPath.mkdirs();
        }
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                suggestedName =  fileName.substring(0,fileName.lastIndexOf("."))+"_"+suggestedName;
                File file = new File(imagepath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "image/" + suggestedName;
            }
        });

        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

        File htmlFile = new File(htmlReallyPath + htmlName);
        OutputStream outStream = new FileOutputStream(htmlFile);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);

        outStream.close();
        return htmlPath + htmlName;
    }

    public void fildDownload(String path, HttpServletResponse response) throws IOException {
        FileInputStream in = new FileInputStream(path);
        String fileName = StringUtils.substring(path, path.lastIndexOf("/"), path.length());
        response.setHeader("Content-Length", String.valueOf(in.available()));
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        OutputStream outputStream = response.getOutputStream();

        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        while ((len = in.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        in.close();
        outputStream.flush();
        outputStream.close();
    }


    /**
     * @since 把pdf文件转为html文件
     * @author wanglishuai
     * @date 2018/11/12 11:09
     * @param file 数据库中的resume文件路径
     * @return html的路径
     * @throws IOException, TransformerException, ParserConfigurationException
     */
    public static String PdfToImage(String file) throws IOException {

        //线上库
        String library ="/mnt/tomcat/apache-tomcat/webapps/";
        //测试库
//        String library = "/usr/local/tomcat/webapps/";
        //pdf文件的名字
        String fileName =file.substring(file.lastIndexOf("/")+1,file.length());
        //pdf文件的物理路径
        String pdfurl = library+"erp/erp_img/";
        //前端访问时用的html的路径
        String htmlPath = "erp/erp_img/"+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //html的物理路径
        String path = library+"erp/erp_img/"+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //如果文件中有图片则图片的路径
        String imagepath = path+"image/";
        //转换后html文件的名字
        String htmlName = fileName.substring(0,fileName.lastIndexOf("."))+".html";

        /*开发时路径
        //pdf文件的名字
        String fileName =file.substring(file.lastIndexOf("/")+1,file.length());
        //pdf文件的物理路径
        String pdfurl = "D:\\mnt\\tomcat\\apache-tomcat\\webapps\\erp\\erp_img\\";
        //前端访问时用的html的路径
        String htmlPath = "html\\recruitManage\\resume\\"+fileName.substring(0,fileName.lastIndexOf("."))+"\\";
        //html的物理路径
        String path = "D:\\ERP_angulajs\\"+htmlPath;
        //如果文件中有图片则图片的路径
        String imagepath = path+"image/";
        //转换后html文件的名字
        String htmlName = fileName.substring(0,fileName.lastIndexOf("."))+".html";*/
        StringBuffer buffer = new StringBuffer();
        FileOutputStream fos;
        PDDocument document;
        File pdfFile;
        int size;
        BufferedImage image;
        FileOutputStream out;
        Long randStr = 0l;
        //PDF转换成HTML保存的文件夹
        File htmlsDir = new File(imagepath);
        if(!htmlsDir.exists()){
            htmlsDir.mkdirs();
        }
        //遍历处理pdf附件
        randStr = System.currentTimeMillis();
        buffer.append("<!doctype html>\r\n");
        buffer.append("<head>\r\n");
        buffer.append("<meta charset=\"UTF-8\">\r\n");
        buffer.append("</head>\r\n");
        buffer.append("<body style=\"background-color:white;\">\r\n");
        buffer.append("<style>\r\n");
        buffer.append("img {background-color:#fff; text-align:center; width:100%; max-width:100%;margin-top:6px;}\r\n");
        buffer.append("</style>\r\n");
        document = new PDDocument();
        //pdf附件
        pdfFile = new File(pdfurl+fileName);
        document = PDDocument.load(pdfFile, (String) null);
        size = document.getNumberOfPages();
        Long start = System.currentTimeMillis(), end = null;
        PDFRenderer reader = new PDFRenderer(document);
        for(int i=0 ; i < size; i++){
            //image = new PDFRenderer(document).renderImageWithDPI(i,130,ImageType.RGB);
            image = reader.renderImage(i, 1.5f);
            //生成图片,保存位置
            out = new FileOutputStream(imagepath + "/"+  fileName.substring(0,fileName.lastIndexOf(".")) + "_" + i + ".jpg");
            ImageIO.write(image, "png", out); //使用png的清晰度
            //将图片路径追加到网页文件里
            buffer.append("<img src=\""+"image" +"\\"+  fileName.substring(0,fileName.lastIndexOf(".")) + "_" + i + ".jpg\"/>\r\n");
            image = null; out.flush(); out.close();
        }
        document.close();
        buffer.append("</body>\r\n");
        buffer.append("</html>");
        end = System.currentTimeMillis() - start;
        System.out.println("===> Reading pdf times: " + (end/1000));
        start = end = null;
        //生成网页文件
        fos = new FileOutputStream(path+htmlName);
        System.out.println(path+randStr+".html");
        fos.write(buffer.toString().getBytes());
        fos.flush(); fos.close();
        buffer.setLength(0);
        return  htmlPath+htmlName;
    }

    /**
     *  @since 把2007版的word即docx文件转为html文件
     * @author wanglishuai
     * @date 2018/11/12 11:09
     * @param file 数据库中的resume文件路径
     * @return html的路径
     * @throws IOException, TransformerException, ParserConfigurationException
     */
    public static String docxToHtml(String file) throws IOException {

//        //线上库
        String library ="/mnt/tomcat/apache-tomcat/webapps/";
        //测试库
//        String library = "/usr/local/tomcat/webapps/";
        //docx文件的名字
        String fileName =file.substring(file.lastIndexOf("/")+1,file.length());
        //docx文件的物理路径
        String filepath = library+"erp/erp_img/";
        //前端访问时用的html的路径
        String htmlPath = "erp/erp_img/"+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //html的物理路径
        String htmlReallyPath = library+"/erp/erp_img/"+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //如果文件中有图片则图片的路径
        final String imagepath = htmlReallyPath;
        //转换后html文件的名字
        String htmlName = fileName.substring(0,fileName.lastIndexOf("."))+".html";


        /*   开发时路径
        //docx文件的名字
        String fileName =file.substring(file.lastIndexOf("/")+1,file.length());
        //docx文件的物理路径
        String filepath = "..\\mnt\\tomcat\\apache-tomcat\\webapps\\erp\\erp_img\\";
        //前端访问时用的html的路径
        String htmlPath = "html\\recruitManage\\resume\\"+fileName.substring(0,fileName.lastIndexOf("."))+"/";
        //html的物理路径
        String htmlReallyPath = "D:\\ERP_angulajs\\"+htmlPath;
        //如果文件中有图片则图片的路径(下面会在东在该路径下增加word/media/)
        final String imagepath = htmlReallyPath;
        //转换后html文件的名字
        String htmlName = fileName.substring(0,fileName.lastIndexOf("."))+".html";*/
        //创建储存html的文件
        File h = new File(htmlReallyPath);
        if (!h.exists()) {
            h.mkdirs();
        }
        File f = new File(filepath+fileName);
            // 1) 加载word文档生成 XWPFDocument对象
            InputStream in = new FileInputStream(f);
            XWPFDocument document = new XWPFDocument(in);
            // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
            File imageFolderFile = new File(imagepath);

            XHTMLOptions options = XHTMLOptions.create();

            options.setExtractor(new FileImageExtractor(imageFolderFile));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
            // 3) 将 XWPFDocument转换成XHTML
            OutputStream out = new FileOutputStream(new File(htmlReallyPath + htmlName));
            XHTMLConverter.getInstance().convert(document, out, options);
            out.close();
        return htmlPath+htmlName;


    }



//    public static void main(String[] args) {
//        File f = new File("E:" + File.separator + "test.txt");
//        try (InputStream inputStream = new FileInputStream(f) {
//        }) {
//            String regEx = "\t";
//            List<List<String>> result = readFileByLine(inputStream, regEx);
//
//            generateFile("e:\\import-order", result, regEx);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
