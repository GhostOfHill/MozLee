package com.mx.core.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

import org.springframework.web.context.ContextLoader;

/**
 * 文件常用工具包
 * @since 1.0
 */
public class FileUtils {
	/**
	 * 读取绝对路径的文件内容
	 * @param filename
	 *            String 绝对路径+文件名+文件类型
	 * @return String
	 */
	public String readTruePathFile(String filename) {
		StringBuffer strBuffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				strBuffer.append(line).append("\r\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
	}

	/**
	 * 读取相对路径的文件串
	 * @param filename
	 *            String 相对路径+文件名+文件类型
	 * @return String
	 */
	public String readFalsePathFile(String filename) {
		return readTruePathFile(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")+filename);
	}

	/**
	 * 写文件
	 * @param strBuffer
	 *            写入的字符串
	 * @param filename
	 *            绝对路径+文件名+文件类型
	 */
	public void write(String strBuffer, String filename) {
		try {
			filename = filename.replaceAll("\\\\", "/");
			File file = new File(filename);
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			osw.write(strBuffer);
			osw.close();
			file.exists();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件结尾处追加数据
	 * @param fileName
	 * @param content
	 */
	public void appendFile(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		new FileUtils().appendFile("C:/upload/aaa.txt","qwerqwer");
	}
	
	/**
	 * 写入文件按相对路径
	 * @param strBuffer
	 * @param filename
	 */
	public void writeFalsePath(String strBuffer, String filename) {
		this.write(strBuffer, ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")+filename);
	}

	/**
	 * 创建文件夹
	 * @param filename
	 */
	public void mkdirs(String filename) {
		String[] dirname = filename.replace("\\", "/").replaceAll("[A-Za-z0-9.]+$", "").split("/");
		StringBuffer filepath = new StringBuffer();
		for (int i = 0; i < dirname.length; i++) {
			File file = new File(filepath.append(dirname[i]).append("/").toString());
			file.mkdir();
		}
	}

	/**
	 * 利用传入的InputStream写文件到filename
	 * @param filename
	 *            String 绝对路径+文件名+文件类型
	 * @param in
	 *            InputStream
	 */
	public void write(String filename, InputStream in) {
		byte[] b = new byte[2048];
		try {
			filename = filename.replaceAll("//", "/");
			File file = new File(filename);
			if (file.isFile()) {
				file.delete();
			}
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			int len = 0;
			do {
				len = in.read(b);
				if (len == -1) break;
				out.write(b, 0, len);
			} while (len != -1);
			file.exists();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取从classpath根目录开始读取文件注意转化成中文
	 * @param path
	 *            String
	 * @return String
	 */
	public String getCPFile(String path) {
		URL url = FileUtils.class.getClassLoader().getResource(path);
		String filepath = url.getFile();
		File file = new File(filepath);
		byte[] retBuffer = new byte[(int) file.length()];
		try {
			FileInputStream fis = new FileInputStream(filepath);
			fis.read(retBuffer);
			fis.close();
			return new String(retBuffer, "UTF-8");
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 利用java本地拷贝文件及文件夹,实现文件夹对文件夹的拷贝
	 * @param objDir
	 *            目标文件夹
	 * @param srcDir
	 *            源的文件夹
	 * @throws IOException
	 */
	public void copyDirectiory(String objDir, String srcDir) throws IOException {
		(new File(objDir)).mkdirs();
		File[] file = (new File(srcDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileInputStream input = new FileInputStream(file[i]);
				FileOutputStream output = new FileOutputStream(objDir + "/" + file[i].getName());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (file[i].isDirectory()) {
				copyDirectiory(objDir + "/" + file[i].getName(), srcDir + "/" + file[i].getName());
			}
		}
	}

	/**
	 * 将一个文件inName拷贝到另外一个文件outName中
	 * @param inName
	 *            源文件路径
	 * @param outName
	 *            目标文件路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void copyFile(String inName, String outName) throws FileNotFoundException, IOException {
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(inName));
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outName));
		copyFile(is, os, true);
	}

	public void copyFile(InputStream is, OutputStream os, boolean close) throws IOException {
		int b;
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		if (close) os.close();
	}

	/**
	 * 拷贝文件
	 * @param inName
	 *            String
	 * @param pw
	 *            PrintWriter
	 * @param close
	 *            boolean 执行完毕后Writer是否要关闭
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void copyFile(String inName, PrintWriter pw, boolean close) throws FileNotFoundException, IOException {
		BufferedReader is = new BufferedReader(new FileReader(inName));
		copyFile(is, pw, close);
	}

	private void copyFile(Reader is, Writer os, boolean close) throws IOException {
		int b;
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		if (close) os.close();
	}

	/**
	 * 从文件inName中读取第一行的内容
	 * @param inName
	 *            源文件路径
	 * @return 第一行的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String readLine(String inName) throws FileNotFoundException, IOException {
		BufferedReader is = new BufferedReader(new FileReader(inName));
		String line = null;
		line = is.readLine();
		is.close();
		return line;
	}

	/**
	 * default buffer size
	 */
	private final int BLKSIZ = 8192;

	/**
	 * 拷贝文件
	 * @param inName
	 *            String 输入文件名
	 * @param outName
	 *            String 输出文件名
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void copyFileBuffered(String inName, String outName) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(inName);
		OutputStream os = new FileOutputStream(outName);
		int count = 0;
		byte b[] = new byte[BLKSIZ];
		while ((count = is.read(b)) != -1) {
			os.write(b, 0, count);
		}
		is.close();
		os.close();
	}

	/**
	 * 将String变成文本文件
	 * @param text
	 *            源String
	 * @param fileName
	 *            目标文件路径
	 * @throws IOException
	 */
	public void stringToFile(String text, String fileName) throws IOException {
		BufferedWriter os = new BufferedWriter(new FileWriter(fileName));
		os.write(text);
		os.flush();
		os.close();
	}

	/**
	 * 打开文件获得BufferedReader
	 * @param fileName
	 *            目标文件路径
	 * @return BufferedReader
	 * @throws IOException
	 */
	public BufferedReader openFile(String fileName) throws IOException {
		return new BufferedReader(new FileReader(fileName));
	}

	/**
	 * 获取文件filePath的字节编码byte[]
	 * @param filePath
	 *            文件全路径
	 * @return 文件内容的字节编码
	 * @roseuid 3FBE26DE027D
	 */
	@SuppressWarnings("unused")
	public byte[] fileToBytes(String filePath) {
		if (filePath == null) {
			return null;
		}
		File tmpFile = new File(filePath);
		if (tmpFile == null) {
			return null;
		}
		byte[] retBuffer = new byte[(int) tmpFile.length()];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			fis.read(retBuffer);
			fis.close();
			return retBuffer;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 将byte[]转化成文件fullFilePath
	 * @param fullFilePath
	 *            文件全路径
	 * @param content
	 *            源byte[]
	 */
	public void bytesToFile(String fullFilePath, byte[] content) {
		if (fullFilePath == null || content == null) {
			return;
		}
		// 创建相应的目录
		File f = new File(getDir(fullFilePath));
		if (f == null || !f.exists()) {
			f.mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(fullFilePath);
			fos.write(content);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据传入的文件全路径，返回文件所在路径
	 * @param fullPath
	 *            文件全路径
	 * @return 文件所在路径
	 */
	public String getDir(String fullPath) {
		int iPos1 = fullPath.lastIndexOf("/");
		int iPos2 = fullPath.lastIndexOf("\\");
		iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);
		return fullPath.substring(0, iPos1 + 1);
	}

	/**
	 * 根据传入的文件全路径，返回文件全名（包括后缀名）
	 * @param fullPath
	 *            文件全路径
	 * @return 文件全名（包括后缀名）
	 */
	public String getFileName(String fullPath) {
		int iPos1 = fullPath.lastIndexOf("/");
		int iPos2 = fullPath.lastIndexOf("\\");
		iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);
		return fullPath.substring(iPos1 + 1);
	}

	/**
	 * 根据传入的文件全名（包括后缀名）或者文件全路径返回文件名（没有后缀名）
	 * @param fullPath
	 *            文件全名（包括后缀名）或者文件全路径
	 * @return 文件名（没有后缀名）
	 */
	public String getPureFileName(String fullPath) {
		String fileFullName = getFileName(fullPath);
		return fileFullName.substring(0, fileFullName.lastIndexOf("."));
	}

	/**
	 * 转换文件路径中的\\为/
	 * @param filePath
	 *            要转换的文件路径
	 * @return String
	 */
	public String wrapFilePath(String filePath) {
		if (filePath.charAt(filePath.length() - 1) != '/') {
			filePath += "/";
		}
		return filePath;
	}

	/**
	 * 删除整个目录path,包括该目录下所有的子目录和文件
	 * @param path
	 */
	@SuppressWarnings("unused")
	public void deleteDirs(String path) {
		File rootFile = new File(path);
		if (rootFile == null) {
			return;
		}
		File[] files = rootFile.listFiles();
		if (files == null) {
			return;
		}
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				deleteDirs(file.getPath());
			} else {
				file.delete();
			}
		}
		rootFile.delete();
	}

	/**
	 * 从InputStream读取字符串
	 * @param is
	 *            InputStream
	 * @return String
	 * @throws IOException
	 */
	public String inputStreamToString(InputStream is) throws IOException {
		return readerToString(new InputStreamReader(is));
	}

	private String readerToString(Reader is) throws IOException {
		StringBuffer sb = new StringBuffer();
		char[] b = new char[BLKSIZ];
		int n;
		while ((n = is.read(b)) > 0) {
			sb.append(b, 0, n);
		}
		return sb.toString();
	}

	/**
	 * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
	 * @param fileNames
	 *            需要修改最后访问时间的文件名数组。
	 * @since 0.1
	 */
	public void touch(String[] fileNames) {
		File[] files = new File[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			files[i] = new File(fileNames[i]);
		}
		touch(files);
	}

	private void touch(File[] files) {
		for (int i = 0; i < files.length; i++) {
			touch(files[i]);
		}
	}

	/**
	 * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
	 * @param fileName
	 *            需要修改最后访问时间的文件名
	 */
	public void touch(String fileName) {
		File file = new File(fileName);
		touch(file);
	}

	private void touch(File file) {
		long currentTime = System.currentTimeMillis();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		file.setLastModified(currentTime);
	}

	/**
	 * 判断指定的文件是否存在。
	 * @param fileName
	 *            要判断的文件的文件名
	 * @return 存在时返回true，否则返回false。
	 * @since 0.1
	 */
	public boolean isFileExist(String fileName) {
		return new File(fileName).isFile();
	}

	/**
	 * 创建指定的目录。 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录。 <b>注意：可能会在返回false的时候创建部分父目录。</b>
	 * @param fileName
	 *            要创建的目录的目录名
	 * @return 完全创建成功时返回true，否则返回false。
	 * @since 0.1
	 */
	public boolean makeDirectory(String fileName) {
		File file = new File(fileName);
		return makeDirectory(file);
	}

	private boolean makeDirectory(File file) {
		File parent = file.getParentFile();
		if (parent != null) {
			return parent.mkdirs();
		}
		return false;
	}

	/**
	 * 清空指定目录中的文件。 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
	 * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
	 * @param directoryName
	 *            要清空的目录的目录名
	 * @return 目录下的所有文件都被成功删除时返回true，否则返回false。
	 * @since 0.1
	 */
	public boolean emptyDirectory(String directoryName) {
		File dir = new File(directoryName);
		return emptyDirectory(dir);
	}

	private boolean emptyDirectory(File directory) {
		File[] entries = directory.listFiles();
		for (int i = 0; i < entries.length; i++) {
			if (!entries[i].delete()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 删除单个文件
	 * @param filePath
	 */
	public void deleteFile(String filePath) {
		new File(filePath).delete();
	}

	/**
	 * 删除指定目录及其中的所有内容。
	 * @param dirName
	 *            要删除的目录的目录名
	 * @return 删除成功时返回true，否则返回false。
	 * @since 0.1
	 */
	public boolean deleteDirectory(String dirName) {
		return deleteDirectory(new File(dirName));
	}

	private boolean deleteDirectory(File dir) {
		if ((dir == null) || !dir.isDirectory()) {
			throw new IllegalArgumentException("Argument " + dir + " is not a directory. ");
		}
		File[] entries = dir.listFiles();
		int sz = entries.length;
		for (int i = 0; i < sz; i++) {
			if (entries[i].isDirectory()) {
				if (!deleteDirectory(entries[i])) {
					return false;
				}
			} else {
				if (!entries[i].delete()) {
					return false;
				}
			}
		}
		if (!dir.delete()) {
			return false;
		}
		return true;
	}

	/**
	 * 列出目录中的所有内容，包括其子目录中的内容。
	 * @param file
	 *            要列出的目录
	 * @param filter
	 *            过滤器
	 * @return 目录内容的文件数组。
	 * @since 0.1
	 */
	public File[] listAll(File file, javax.swing.filechooser.FileFilter filter) {
		ArrayList<File> list = new ArrayList<File>();
		File[] files;
		if (!file.exists() || file.isFile()) {
			return null;
		}
		list(list, file, filter);
		files = new File[list.size()];
		list.toArray(files);
		return files;
	}

	/**
	 * 将目录中的内容添加到列表。
	 * @param list
	 *            文件列表
	 * @param filter
	 *            过滤器
	 * @param file
	 *            目录
	 */
	private void list(ArrayList<File> list, File file, javax.swing.filechooser.FileFilter filter) {
		if (filter.accept(file)) {
			list.add(file);
			if (file.isFile()) {
				return;
			}
		}
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				list(list, files[i], filter);
			}
		}
	}

	/**
	 * 返回文件的URL地址。
	 * @param file
	 *            文件
	 * @return 文件对应的的URL地址
	 * @throws MalformedURLException
	 * @since 0.4
	 * @deprecated 在实现的时候没有注意到File类本身带一个toURL方法将文件路径转换为URL。 请使用File.toURL方法。
	 */
	public URL getURL(File file) throws Exception {
		String fileURL = "file:/" + file.getAbsolutePath();
		URL url = new URL(fileURL);
		return url;
	}

	/**
	 * 从文件名得到文件绝对路径。
	 * @param fileName
	 *            文件名
	 * @return 对应的文件路径
	 * @since 0.4
	 */
	public String getFilePath(String fileName) {
		File file = new File(fileName);
		return file.getAbsolutePath();
	}

	/**
	 * 得到文件的类型。 实际上就是得到文件名中最后一个“.”后面的部分。
	 * @param fileName
	 *            文件名
	 * @return 文件名中的类型部分
	 * @since 0.5
	 */
	public String getFileType(String fileName) {
		int point = fileName.lastIndexOf('.');
		int length = fileName.length();
		if (point == -1 || point == length - 1) {
			return "";
		} else {
			return fileName.substring(point + 1, length);
		}
	}

	public String getFileType(File file) {
		return getFileType(file.getName());
	}

	/**
	 * 将文件名中的类型部分去掉。
	 * @param filename
	 *            文件名
	 * @return 去掉类型部分的结果
	 * @since 0.5
	 */
	public String getFileNameTrimType(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			return filename.substring(0, index);
		} else {
			return filename;
		}
	}

	/**
	 * 得到相对路径。 文件名不是目录名的子节点时返回文件名。
	 * @param pathName
	 *            目录名
	 * @param fileName
	 *            文件名
	 * @return 得到文件名相对于目录名的相对路径，目录下不存在该文件时返回文件名
	 * @since 0.5
	 */
	public String getSubpath(String pathName, String fileName) {
		int index = fileName.indexOf(pathName);
		if (index != -1) {
			return fileName.substring(index + pathName.length() + 1);
		} else {
			return fileName;
		}
	}

	/**
	 * 追加文件内容
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            新增内容
	 */
	public void appendMethod(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将文件夹压缩
	 * @param zipFileName
	 *            压缩后文件保存路径/文件名.后缀
	 * @param inputFileName
	 *            需要压缩的文件夹路径
	 * @throws Exception
	 */
	public void zip(String zipFileName, String inputFileName) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, new File(inputFileName), "");
		out.close();
	}

	private void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++)
				zip(out, fl[i], base + fl[i].getName());
		} else {
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
			in.close();
		}
	}

	/**
	 * 为文件重命名
	 * @param file
	 *            原始文件
	 * @param renameSrcPath
	 *            重命名后的文件的绝对路径 如"d:/"
	 * @param renameZipFileName
	 *            重命名的文件名称
	 */
	public void renameFile(File file, String renameSrcPath, String renameZipFileName) {
		File renameFile = new File(renameSrcPath, renameZipFileName);
		file.renameTo(renameFile);
	}
}
