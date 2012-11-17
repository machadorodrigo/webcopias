package br.com.webcopias.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.webcopias.dao.CentralCopyImpl;
import br.com.webcopias.dao.DocumentImpl;
import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.model.Document;

/**
 * Servlet implementation class DownloadFile
 */
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("taskid");
		byte[] buffer = new byte[1024];
		int fileLength;
		
		FileInputStream input = null;
		ServletOutputStream out = null;
		
		Document document = null;
		CentralCopy centralCopy = null;
		
		CentralCopyImpl centralCopyImpl = new CentralCopyImpl();
		DocumentImpl documentImpl = new DocumentImpl();
		
		centralCopy = centralCopyImpl.getCentralCopy(Integer.parseInt(id));
		document = documentImpl.getDocument(centralCopy.getDocument());
		
		String fullPath = document.getDocumentPath()+"\\"+document.getId()+"\\"+document.getDocumentName()+"."+document.getDocumentType();
		
		response.setHeader("Content-Disposition", "attachment;filename=\""+(document.getDocumentName()+"."+document.getDocumentType())+"\"");
		response.setHeader("Content-Type", this.prepareContentType(document.getDocumentType()));
		response.setHeader("Accept-Ranges", "bytes");
		response.setContentType("application/octet-stream");
		
		try{
			input = new FileInputStream(fullPath);
			out = response.getOutputStream();

			while((fileLength = input.read(buffer)) > 0){
				out.write(buffer, 0, fileLength);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			input.close();

			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String prepareContentType(String docType){
		String contentType = null;
		
		if(docType.equals("jpg")){
			contentType = "image/jpg";
		}else if(docType.equals("jpeg")){
			contentType = "image/jpg";
		}else if(docType.equals("png")){
			contentType = "image/png";
		}else if(docType.equals("pdf")){
			contentType = "application/pdf";
		}else if(docType.equals("doc")){
			contentType = "application/msword";
		}else if(docType.equals("docx")){
			contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}else if(docType.equals("xls")){
			contentType = "application/vnd.ms-excel";
		}else if(docType.equals("xlsx")){
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}else{
			contentType = "text";
		}
		return contentType;
	}
}
