package tr.com.huseyinaydin.tasit;

import tr.com.huseyinaydin.base.Arac;
import tr.com.huseyinaydin.kural.IKanun;
import tr.com.huseyinaydin.kural.ITaksicilerDernegi;
import tr.com.huseyinaydin.kural.IUlastirmaBakanligi;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString (callSuper = true)
public class Taksi extends Arac implements IKanun, IUlastirmaBakanligi, ITaksicilerDernegi {

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


    @Override
    public Boolean ruhsatDurumu(Boolean durum) {
        return durum;
    }

    @Override
    public boolean ehliyetDurumu(boolean durum) {
        return false;
    }

    @Override
    public boolean dernekUyelikDurumu(boolean durumu) {
        return durumu;
    }

    @Override
    public float acilisUcreti(float para) {
        return para;
    }
}