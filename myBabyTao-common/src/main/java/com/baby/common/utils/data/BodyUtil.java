package com.baby.common.utils.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xcy on 2017-09-26.
 */
public class BodyUtil {

    /**
     * 过滤新编辑器中的菜单div等
     * */
    public static String RemoveEditorBlock(String body)
    {
        //第一遍，过滤内部菜单div
        Pattern patternDiv = Pattern.compile("(<div class=\"edit-box(top|left|right|bottom|topblock).*?</div>)");
        Matcher matcherDiv = patternDiv.matcher(body);
        while (matcherDiv.find()) {
            body=body.replace(matcherDiv.group(),"");
        }

        //第二遍
        patternDiv = Pattern.compile("(<div class=\"Tem_EmptyMessage.*?</div>)");
        matcherDiv = patternDiv.matcher(body);
        if(matcherDiv.find()){
            body=body.replace(matcherDiv.group(0),"");
        }

        //第三遍,针对 divBlock,仅返回table组合在一起的值
        patternDiv = Pattern.compile("(<div\\sclass=\"divBlock((?!divBlock).)*\\/div>)");
        matcherDiv = patternDiv.matcher(body);
        while (matcherDiv.find()) {
            String div=matcherDiv.group();
            //查找链接
            Pattern patternTable = Pattern.compile("(<table.*class=\"Tem_FullBlock.*</table>)");
            Matcher matcherTable = patternTable.matcher(div);
            if (matcherTable.find()) {
                body.replace(div,matcherTable.group(1));
            }
        }

        //第四遍,移除contenteditable="true"
        body=body.replace("contenteditable=\"true\"","");

        return body;
    }

    /**
     * script标签及标签内容一同清空
     * */
    public static String ClearScript(String body) {
        while (body.indexOf("<script") > -1 || body.indexOf("&lt;script") > -1) {
            // 编译正则表达式
            Pattern pattern = Pattern.compile("(<script.*?</script>)");
            Matcher matcher = pattern.matcher(body);
            if(matcher.find())
                body=body.replace(matcher.group(0),"");
            else
                body = body.replace("<script>", "").replace("<script", "").replace("</script\\>","");

            pattern = Pattern.compile("(&lt;script.*?script&gt;)");
            matcher= pattern.matcher(body);
            if(matcher.find())
                body=body.replace(matcher.group(0),"");
            else
                body = body.replace("&lt;script&gt;", "").replace("&lt;script", "");
        }
        //同时去掉不完整的<script></script>标签
        return body.replace("</script>", "").replace("/script>", "").replace("<script", "").replace("&lt;script", "");
    }
}
