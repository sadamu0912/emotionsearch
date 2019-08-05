(function($){
	var Page ={}
	
	 Page.Event={
			reqUrl:{
			'get':'/login'	
			},
			init:function(){
				this.loginBtnClick();
			},
		loginBtnClick:function(){
			$('#loginButton').click(function(){
				event.preventDefault();
				$('form').fadeOut(500);
				$('.wrapper').addClass('form-success');
				var form =$('#loginForm');
				var array = form.serializeArray();
				var o={}
				serializeFrom(array,o);
				//httpHelper.get(o,Page.Event.reqUrl,Page.Event.loginSuccess,Page.Event.loginError);
				location.href="/login?username="+o.username+"&password="+o.password;

			})
		},
		loginSuccess:function(res){
			if(res.state=="200"){
				console.log('登录成功');
			}
		},
		loginError:function(){
			console.log('失败');
		}
	}
	Page.Event.init();
})(jQuery);