/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web;

import com.jcraft.jsch.Session;
import lombok.Data;
import space.nature.common.core.util.JaxbUtils;
import space.nature.common.core.util.SftpUtils;
import space.nature.common.core.util.ZipUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement(name = "test")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Test {

    @XmlAttribute
    private String name;

    public static void main(String[] args) throws Exception {
        Session session = SftpUtils.session(null);
        SftpUtils.execCommand(session, null, null, null);
        ZipUtils.zip("D:/logs");
        ZipUtils.unZip(new File("D:/logs.zip"), "D:/test/logs");
        String xml = JaxbUtils.toXml(new Test(), "UTF-8", false);
        System.out.println(xml);
        int a = 3;
        int b = 2;
        System.out.println("------->" + (2 & 1));
        System.out.println("------->" + (a | b));
    }


}
