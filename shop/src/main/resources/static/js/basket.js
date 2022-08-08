let index2 = {

	init: function() {
		$("#inputCart").bind("click", () => {
			this.putItemCart();
		});

	},

	putItemCart: function() {
		
		let userid = $('#userId').val();
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		let itemId = $("#inputCart").val();

		let data = {
			itemId: $('#inputCart').val()
		}

		$.ajax({
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token)				
			},
			type: "POST",
			url: `/test/api/cart?itemId=${itemId}`,
			dataType: "json"
		})
			.done(function(data) {
				alert("장바구니에 추가되었습니다.");
			})
			.fail(function(error) {
				if(userid != null) {
					alert("장바구니에 추가하지 못했습니다.");
				} else {
					if(confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")) {
						location.href="/security/login_form";
					} 
					
				}
				
				
				
			});

	},

}

index2.init();

function basketItemDelete(basket) {
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$.ajax({
		beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token)				
		},
		type: "DELETE",
		url: `/test/api/basket/${basket}`,
		dataType: "json"
	}).done(function(res) {
		console.log();
		location.href = `/shop/basket_form/${res.data}`
	}).fail(function() {
		alert("취소 실패");
	});
}
