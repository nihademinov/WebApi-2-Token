package org.example.webapi2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.webapi2.api.model.Contact;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


//fixme Class annotation lar ile class arasinda bu kimi bosluqlar buraxmaq lazim deyil.
// umumilikde her bir halda codu refaktor etmek gerekir. (ctrl + alt + o/ ctrl + alt + l)
public class RegisterRequestDto {


    //fixme butun gerekli parameterlerin uzerinde Validation Annotation lar qeyd olunmalidir
    // contoller de ise hemin requestBody hemcinin @Valid annotation ile validation aparilmali oldugu qeyd olunmalidir
    // example: (@Valid  @RequestBody RegisterRequestDto request)
    // @NotEmpty
    private String email;

    //fixme @NotEmpty , diger obyekt tiplerinde @NotNull kimi check ler aparila biler,
    // annotationlarin istenilen neticeni verdiyinden emin olaq.
    // service seviyesinde aparilacaq custom validation lar bunlardan elavedir, "istifadeci adi atrtiq istofade olunub" kimi

    private String firstName;
    private String lastName;
    private String password;
    private String configPassword;
    private List<String> roles;
    private List<Contact> contacts = new ArrayList<>();
}
