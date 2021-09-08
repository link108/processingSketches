package Pixels;

//import java.nio.file.Path;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDownloader {

  String url;

  public ImageDownloader(String url) {
    this.url = url;
  }

  public Path downloadImage() {
    Path outputFile;
    String extension = url.substring(url.lastIndexOf("."));
    String fileAndExtension = url.substring(url.lastIndexOf("/"));
    System.out.println("here");
    try {
      String tmpdir = Files.createTempDirectory("tmpDirPrefix").toFile().getAbsolutePath();
      outputFile = Paths.get(tmpdir + "/" + fileAndExtension);
      InputStream in = new URL(url).openStream();
      Files.copy(in, outputFile);
      System.out.println("Wrote image to " + outputFile);
    } catch (IOException e){
      return null;
    }
    return outputFile;
  }
}
