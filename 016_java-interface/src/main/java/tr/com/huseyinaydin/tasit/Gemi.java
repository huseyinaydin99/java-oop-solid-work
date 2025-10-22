package tr.com.huseyinaydin.tasit;

import tr.com.huseyinaydin.base.Arac;
import tr.com.huseyinaydin.kural.IKanun;

public class Gemi extends Arac implements IKanun {
    @Override
    public int hizSiniri(int hizLimiti) {
        return hizLimiti;
    }

    @Override
    public String bakimDurumu(String durumBilgisi) {
        return durumBilgisi;
    }

    @Override
    public float tasitVergisi(float vergiMiktari) {
        return vergiMiktari;
    }

    @Override
    public byte emisyonDegeri(byte olcumOrani) {
        return olcumOrani;
    }
}
