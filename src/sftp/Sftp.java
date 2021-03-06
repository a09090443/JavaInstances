package sftp;

import java.io.File;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class Sftp {
	/*
	 * args[0]: hostName args[1]: username args[2]: password args[3]:
	 * localFilePath args[4]: remoteFilePath
	 */
	public static void main(String[] args) {
//		if (args.length < 5)
//			throw new RuntimeException(
//					"Error. Please enter "
//							+ "args[0]: hostName, args[1]: username, args[2]: password, "
//							+ "args[3]: localFilePath, args[4]: remoteFilePath.");

//		upload(args[0], args[1], args[2], args[3], args[4]);
//		exist(args[0], args[1], args[2], args[4]);
//		download(args[0], args[1], args[2], args[3], args[4]);
//		delete(args[0], args[1], args[2], args[4]);
		upload("172.22.16.42", "eivadmin", "eivadmin", "/home/zipe/tmp/EIVPOS_43322131_20130530_01_old.zip", "/test/test");

	}

	public static void upload(String hostName, String username,
			String password, String localFilePath, String remoteFilePath) {

		File f = new File(localFilePath);
		if (!f.exists())
			throw new RuntimeException("Error. Local file not found");

		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			// Create local file object
			FileObject localFile = manager.resolveFile(f.getAbsolutePath());

			// Create remote file object
			FileObject remoteFile = manager.resolveFile(
					createConnectionString(hostName, username, password,
							remoteFilePath), createDefaultOptions());

			// Copy local file to sftp server
			remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);

			System.out.println("File upload success");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static void download(String hostName, String username,
			String password, String localFilePath, String remoteFilePath) {

		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			String downloadFilePath = localFilePath.substring(0,
					localFilePath.lastIndexOf("."))
					+ "_downlaod_from_sftp"
					+ localFilePath.substring(localFilePath.lastIndexOf("."),
							localFilePath.length());

			// Create local file object
			FileObject localFile = manager.resolveFile(downloadFilePath);

			// Create remote file object
			FileObject remoteFile = manager.resolveFile(
					createConnectionString(hostName, username, password,
							remoteFilePath), createDefaultOptions());

			// Copy local file to sftp server
			localFile.copyFrom(remoteFile, Selectors.SELECT_SELF);

			System.out.println("File download success");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static void delete(String hostName, String username,
			String password, String remoteFilePath) {
		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			// Create remote object
			FileObject remoteFile = manager.resolveFile(
					createConnectionString(hostName, username, password,
							remoteFilePath), createDefaultOptions());

			if (remoteFile.exists()) {
				remoteFile.delete();
				System.out.println("Delete remote file success");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static boolean exist(String hostName, String username,
			String password, String remoteFilePath) {
		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			// Create remote object
			FileObject remoteFile = manager.resolveFile(
					createConnectionString(hostName, username, password,
							remoteFilePath), createDefaultOptions());

			System.out.println("File exist: " + remoteFile.exists());

			return remoteFile.exists();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static String createConnectionString(String hostName,
			String username, String password, String remoteFilePath) {
		// result: "sftp://user:123456@domainname.com/resume.pdf
		return "sftp://" + username + ":" + password + "@" + hostName + "/"
				+ remoteFilePath;
	}

	public static FileSystemOptions createDefaultOptions()
			throws FileSystemException {
		// Create SFTP options
		FileSystemOptions opts = new FileSystemOptions();

		// SSH Key checking
		SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
				opts, "no");

		// Root directory set to user home
		SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

		// Timeout is count by Milliseconds
		SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

		return opts;
	}
}
