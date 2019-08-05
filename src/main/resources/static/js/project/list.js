(function($){
	var Page ={}
	
	 Page.Event={
			reqUrl:{
			'get':'/login'	
			},
			init:function(){
				this.picHoverChangeOpacity();
				this.changeSort();
				this.displayOperation();
				this.editProject();
				getProgressBar();
			},
			/**
			 * 当鼠标停在图片上的时候，改变透明度
			 */
		picHoverChangeOpacity:function(){
			$('#top10project').on('mouseenter','img',function(event){
				$(this).addClass('hoverd');
			})
			$('#top10project').on('mouseleave','img',function(event){
				$(this).removeClass('hoverd');
			})
		},
		/**
		 * 点击图标改变排序的顺序
		 */
		changeSort:function(){
			$('.main-content').on('click','.canOrderTHead',function(event){
				var icon = $(this).find('i');
				if(icon.hasClass('fa-sort-up')){
					icon.removeClass('fa-sort-up').addClass('fa-sort-desc');
					
				}else if(icon.hasClass('fa-sort-desc')){
					icon.removeClass('fa-sort-desc').addClass('fa-sort-up');	
				}
				
			})
		},
		displayOperation:function(){
			$('#projectTable').on('mouseenter','.projectNameContent',function(event){
				$(this).find('img').removeClass('hide');
			})
			$('#projectTable').on('mouseleave','.projectNameContent',function(event){
				$(this).find('img').addClass('hide');
			})
		},
		editProject:function(){
			$('.main-content').on('click','.edit',function(event){
				var projectId = $(this).parents('tr').find('input[name="projectId"]').attr('data-projectId');
				var _url=String.format("/project/getProjectLayerById?projectId={0}",projectId);
				top.project =layer.open({
					type:2,
					title:'项目编辑',
					shadeClose:true,
					shade:0,
					anim:1,
					btn:['保存','取消'],
					content:[_url,'auto'],
					area:['780px','450px'],
					'zIndex':'1000',
					btn1:function(){
						layer.close(top.project);
					},
					btn2:function(){
						layer.close(top.project);
					}
					
				});
			})
		}
	}
	Page.Event.init();
})(jQuery);