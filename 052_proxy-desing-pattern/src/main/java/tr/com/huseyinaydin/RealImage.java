package tr.com.huseyinaydin;

public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Diskten ağır bir şekilde yükleniyor: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Ekranda gösteriliyor: " + fileName);
    }
}