package tr.com.huseyinaydin.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Arac {
    private short koltukSayi;
    private byte tekerSayisi;
    private String marka;
    private int motorHacmi;
    private String yakitTipi;
}