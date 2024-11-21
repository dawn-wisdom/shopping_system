package controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;

import com.aliyun.oss.model.PutObjectRequest;
import entity.Product;
import entity.Seller;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.UUID;

@WebServlet("/EditProductServlet")
@MultipartConfig
public class EditProductServlet extends HttpServlet {
    ProductService productService=new ProductService();
    private static final String OSS_ENDPOINT = "oss-cn-guangzhou.aliyuncs.com";  // 设置为你的 OSS 区域
    private static final String ACCESS_KEY_ID = "LTAI5tAZf7LdGAiPUmRVRrUh";  // 你的阿里云 AccessKeyId
    private static final String ACCESS_KEY_SECRET = "dyhjo6O89mpXl5r4nP2l3qzjsQUE7n";  // 你的阿里云 AccessKeySecret
    private static final String BUCKET_NAME = "bucket4product";  // 你的 OSS 存储桶名称
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        //新的商品的记录
        Product product=new Product();

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            System.out.println(paramName + ": " + request.getParameter(paramName));
        }
        // 获取上传的文件
        request.setCharacterEncoding("UTF-8");  // 设置请求字符编码
        response.setContentType("text/html;charset=UTF-8");  // 设置响应字符编码
        Part filePart = request.getPart("image");  // "image" 是 HTML 表单中 file input 的 name 属性
        String fileName = getFileName(filePart);  // 获取文件名
        String imageUrl=null;
        if (fileName == null || fileName.isEmpty()) {
            imageUrl=request.getParameter("productImage");
        }
        else
        {
            // 获取文件输入流
            InputStream inputStream = filePart.getInputStream();

            // 创建 OSS 客户端
            OSS ossClient = new OSSClientBuilder().build(OSS_ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            String objectKey = "images/" + UUID.randomUUID().toString() + "_" + fileName;
            try {
                response.setContentType("text/html");
                // 创建一个唯一的文件名，避免文件名冲突

                //response.getWriter().write("文件名为： "+objectKey+"<br>");
                // 上传文件到 OSS
                PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectKey, inputStream);
                ossClient.putObject(putObjectRequest);

                // 返回上传成功的结果
                response.setContentType("text/html");
                //response.getWriter().write("File uploaded successfully. <br>File URL: https://your-bucket-name.oss-cn-region.aliyuncs.com/" + objectKey);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error uploading file.");
            } finally {
                // 关闭 OSS 客户端
                ossClient.shutdown();
            }

            String ossUrl = String.format("https://%s.%s/%s", BUCKET_NAME, OSS_ENDPOINT, objectKey);

            product.setProductImage(ossUrl);
        }

        product.setProductId(Integer.parseInt(request.getParameter("productId")));
        product.setProductName(request.getParameter("productName"));
        product.setProductPrice(Double.parseDouble(request.getParameter("productPrice")));
        product.setPnum(Integer.parseInt(request.getParameter("pnum")));
        product.setCategory(request.getParameter("category"));
        product.setDescription(request.getParameter("description"));
        /*HttpSession session = request.getSession();
        Seller seller=(Seller)session.getAttribute("seller");
        product.setStore(seller.getStoreName());
        */
        product.setStore("天天特卖工厂");
        productService.editProduct(product);
        //response.getWriter().write("<br>add product successfully.");
        //request.getRequestDispatcher("productsInStore.jsp").forward(request, response);// 转发
        String url="ShowProductInStore";
        response.sendRedirect(url);
    }

    // 获取上传文件的文件名
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("Content-Disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                String fileName = element.substring(element.indexOf("=") + 2, element.length() - 1);
                return fileName;
            }
        }
        return null;
    }
}

