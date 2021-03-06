package com.imooc.servlet;

import com.imooc.domain.User;
import com.imooc.utils.UploadUtils;
import com.sun.deploy.util.StringUtils;
import com.sun.glass.ui.Application;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feiwu on 12/9/18.
 */
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
            List<String> hobbyList = new ArrayList<>();
            Map<String, String> userMap = new HashMap<>();
            String url = "";
            for(FileItem item : fileItemList) {
                if(item.isFormField()) {
                    String key = item.getFieldName();
                    String value = item.getString("UTF-8");
                    System.out.println(key + "===>" + value);
                    userMap.put(key, value);
                }else if(!item.getName().isEmpty()) {
                    String fileName = item.getName();
                    String uuidFileName = UploadUtils.getUUIDFileName(fileName);
                    InputStream is = item.getInputStream();
                    String path = this.getServletContext().getRealPath("/upload");
                    url = path + "\\" + uuidFileName;
                    OutputStream os = new FileOutputStream(url);
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1){
                        os.write(b, 0, len);
                    }
                    is.close();
                    os.close();
                }
                userMap.put("path", url);
            }

            List<User> userList = (List<User>) this.getServletContext().getAttribute("userList");
            for(User item : userList) {
                if(item.getUsername().equals(userMap.get("username"))) {
                    request.setAttribute("msg", "username already exist");
                    request.getRequestDispatcher("/regist.jsp").forward(request, response);
                    return;
                }
            }

            User user = new User();
            user.setUsername(userMap.get("username"));
            user.setPassword(userMap.get("password"));
            user.setNickname(userMap.get("nickname"));
            user.setSex(userMap.get("sex"));
            user.setHobby(userMap.get("hobby"));
            user.setPath(userMap.get("path"));

            userList.add(user);
            this.getServletContext().setAttribute("userList", userList);
            System.out.println(userList);
            this.getServletContext().setAttribute("username", userMap.get("username"));
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
