<script type="text/javascript" src="${basePath}/static/js/a.js"></script>
<#list users as user>
	${user.username}  || ${user.password} <br/>
</#list>