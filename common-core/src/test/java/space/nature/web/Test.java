/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web;

import com.jcraft.jsch.JSchException;
import lombok.Data;
import space.nature.util.JaxbUtils;
import space.nature.util.ZipUtils;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

@XmlRootElement(name = "test")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Test {

    @XmlAttribute
    private String name;

    public static void main(String[] args) throws IOException, JSchException, InterruptedException, JAXBException {
//        Session session = SftpUtils.session(null);
//        SftpUtils.execCommand(session, null, null, null);
        ZipUtils.zip("D:/logs");
//        ZipUtils.unZip(new File("D:/logs.zip"), "D:/test/logs");
        String xml = JaxbUtils.toXml(new Test(), "UTF-8", false);
        System.out.println(xml);
    }

}
