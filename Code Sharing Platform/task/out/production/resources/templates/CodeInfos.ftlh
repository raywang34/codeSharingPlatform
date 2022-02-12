<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list codeInfos as codeInfo>
        <span id="load_date">${(codeInfo.date).format('yyyy/MM/dd hh:mm:ss')}</span>
        <br/>
        <#if codeInfo.originalViews != 0>
            <span id="views_restriction"><u>${codeInfo.views}</u> more views allow</span>
            <br/>
        </#if>
        <#if codeInfo.originalTime != 0>
            <span id="time_restriction">The code will be available for <u>${codeInfo.time}</u> seconds</span>
            <br/>
        </#if>
        <pre id="code_snippet"><code>${codeInfo.code}</code></pre>
    </#list>
</body>
</html>