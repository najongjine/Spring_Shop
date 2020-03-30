$(function() {
	$("#call_msg").click(function() {
		$.ajax({
			url:rootPath+"/ajax/msg",
			method:"GET",
			data:{msg:$("#msg").val()},
			success:function(result){
				if(result=="ERROR"){
					alert("서버에 전달하는 문자열이 잘못되었음")
				}
				else{
					alert(result)
				}
				
			},
			error:function(error){
				alert("서버 통신 오류")
			}
		})
	})
	$(document).on("click","#call_addr",function(){
		$.ajax({
			url:rootPath+"/ajax/addr",
			method:"GET",
			success:function(result){
				alert(JSON.stringify(result))
				
				/*
				 * json으로 받은 객체를 html tag로 묶어서 DOM 문서를 만들고 html 코드로 붙여넣기
				 */
				var addr="<div>"+result.ad_name+"</div>"
				addr+="<div>"+result.ad_addr+"</div>"
				addr+="<div>"+result.ad_tel+"</div>"
				addr+="<div>"+result.ad_age+"</div>"
				$("#sub").html(addr)
			},
			error:function(){
				alert("서버 통신 오류")
			}
		})
	})
	$(document).on("click","#call_addr_view",function(){
		$.ajax({
			url:rootPath+"/ajax/addr_view",
			success:function(result){
				$("#sub").html(result)
			}
		})
	})
	
	/*
	 * ad_name id 인 tag는 ajax를 통해서 나중에 동적으로 붙여넣기 함.
	 * 이런경우 일반적인 click 이벤트는 작동을 하지 않는다.
	 */
//	$("#ad_name").click(function() {
//		alert($(this).text())
//	})
	$(document).on("click","#ad_name",function(){
		/*
		 * 톰캣은 기본적으로 post,get method는 사용할수 있는데
		 * put,delete 등 의 restful method는 사용을 할수 없도록기본값이 막혀있다.
		 */
		$.ajax({
			url:rootPath+"/ajax/put",
			method:"PUT",
			data:{msg:$(this).text()},
			success:function(result){
				alert(result)
			},
			error:function(){
				alert("통신 오류")
			}
		})
	})
})