package com.appiron.appleservices.dep;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.XmlUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 自定义的
 * <p>
 * 移除激活锁
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
public class EscrowKeyUnlockStatusResponse implements Serializable {

    private static final long serialVersionUID = 42L;

    private String content;

    private int statusCode;

    public EscrowKeyUnlockStatusResponse(String content, int statusCode) {
        this.content = content;
        this.statusCode = statusCode;
    }

    @Data
    public static class Info {

        @JsonProperty("version")
        private Integer version;

        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;
    }

    public Info info() throws IOException, SAXException {
        if (ObjectUtil.isEmpty(content)) {
            return null;
        }
        Document parse = XmlUtil.createDocumentBuilder().parse(IoUtil.toStream(content.getBytes(StandardCharsets.UTF_8)));
        Info info = new Info();
        listNodes(parse.getDocumentElement(), info);
        return info;
    }

    /**
     * 遍历根据节点对象下面的所有的节点对象
     */
    private void listNodes(Node node, Info info) {
        // 节点是什么类型的节点
        if (node.getNodeType() == Node.ELEMENT_NODE) {// 判断是否是元素节点
            Element element = (Element) node;
            //判断此元素节点是否有属性
            if (element.hasAttributes()) {
                //获取属性节点的集合
                NamedNodeMap attributes = element.getAttributes();//Node
                //遍历属性节点的集合
                for (int k = 0; k < attributes.getLength(); k++) {
                    //获取具体的某个属性节点
                    Attr attr = (Attr) attributes.item(k);

                    if ("version".equals(attr.getNodeName())) {
                        info.setVersion(Integer.valueOf(attr.getNodeValue()));
                    } else if ("code".equals(attr.getNodeName())) {
                        info.setCode(attr.getNodeValue());
                    } else if ("message".equals(attr.getNodeName())) {
                        info.setMessage(attr.getNodeValue());
                    }
                }
            }
            //获取元素节点的所有孩子节点
            NodeList childNodes = element.getChildNodes();
            //遍历
            for (int j = 0; j < childNodes.getLength(); j++) {
                //得到某个具体的节点对象
                Node nd = childNodes.item(j);
                if ("version".equals(nd.getNodeName())) {
                    info.setVersion(Integer.valueOf(nd.getNodeValue()));
                } else if ("code".equals(nd.getNodeName())) {
                    info.setCode(nd.getNodeValue());
                } else if ("message".equals(nd.getNodeName())) {
                    info.setMessage(nd.getNodeValue());
                }
                //重新调用遍历节点的操作的方法
                listNodes(nd, info);
            }

        }
    }
}
