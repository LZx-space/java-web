package space.nature.javaweb;

import lombok.Data;
import space.nature.javaweb.infrastructure.util.JaxbUtils;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "test")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Test {

    @XmlAttribute
    private String name;

    public static void main(String[] args) {
        Test test = new Test();
        test.setName("a");
        try {
            int nano = LocalDateTime.now().getNano();
            String s = JaxbUtils.toXml(test, "utf-8", true);

            System.out.println(s + "\n" + (LocalDateTime.now().getNano() - nano));
            int i = 0;
            while (i < 50) {
                nano = LocalDateTime.now().getNano();
                s = JaxbUtils.toXml(test, "utf-8", true);
                System.out.println(s + "\n" + (LocalDateTime.now().getNano() - nano));
                i++;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
