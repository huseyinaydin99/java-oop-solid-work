package tr.com.huseyinaydin;

public class Main {
    public static void main(String[] args) {
        EtlProcessTemplate csvExport = new DatabaseToCsvEtl();
        csvExport.executeProcess();

        EtlProcessTemplate cloudBackup = new ApiToCloudEtl();
        cloudBackup.executeProcess();
    }
}