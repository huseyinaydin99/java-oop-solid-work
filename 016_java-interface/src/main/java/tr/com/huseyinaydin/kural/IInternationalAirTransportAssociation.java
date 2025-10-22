package tr.com.huseyinaydin.kural;

public interface IInternationalAirTransportAssociation {
    public Boolean kurumUyeDurumu(boolean durumu);
    public float aidat(float aidatOdemesi);
    public boolean vizeIzinDurumu(boolean durum);
    public short yolcuKapasitesi(short koltukSayisi);
}