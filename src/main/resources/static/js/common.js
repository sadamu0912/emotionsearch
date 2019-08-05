function setCookie(name, value) {
var Days = 30;
var exp = new Date();
exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
document. cookie = name +  + escape(value) + ";expires=" + exp.toGMTString();
}


/**
 * 讲表单序列化城对象  如果是checkbox的话，选中多个，一个name，就对应一个数组
 * @param arr
 * @param o
 * @returns
 */
function serializeFrom (arr , o){
	$(arr).each(function (index, el) {
	if(o[el.name]==undefined ||o[el.name]== ""){
		o[el.name] = el.value;
	}else{
		if(o[el.name].constructor.name=="String"){
			var array = new Array();
			array.push(o[el.name]);
			array.push(el.value);
			o[el.name]= array;
		}else{
			console.log(o[el.name].constructor.name);
			var array = new Array();
			for(var i=0;i<o[el.name].length;i++){
			array.push(o[el.name][i]);
			}
			array.push(el.value);
			o[el.name]= array;
		}
	}
	})
}

function loadFun(url){
	var oHead = document.getElementsByTagName('HEAD').item(0); 
	var oScript= document.createElement("script"); 
	oScript.type = "text/javascript"; 
	oScript.src=url; 
	oHead.appendChild( oScript); 
}
function getProgressBar(){
	var progressBars= $('.progress-bar');
	$.each(progressBars,function(index,el){
	var	percent =$(el).attr('data-progress');
	$(el).width(percent+"%");
	$(el).find('.progressText').text(percent+"%");
	})
}
/**
 * ajax方法 封装
 */
var httpHelper = {
		'save':function (data,requestUrl,successcb,errorcb)  { 
		this.server(requestUrl.save, "POST", data, successcb, errorcb);
		},
		'delete':function (data,requestUrl,successcb,errorcb) {
		this.server(requestUrl.save, "DELETE", data, successcb, errorcb);
		},
		'update': function (data, requestUrl, successcb, errorcb) {
		this.server(requestUrl.save, "PUT", data, successcb, errorcb);
		},
		'get': function (data, requestUrl, successcb, errorcb) {
		this.server(requestUrl.get, "GET", data, successcb, errorcb);
		},
		'getAll' : function (data, requestUrl, successcb, errorcb) {
		this.server(requestUrl.getAll, "GET", data, successcb, errorcb);
		},
		'server': function (requestUrl, type, arg, successcb, errorcb) {
			var data;
			if (!type) type = "GET";
			if (type.toUpperCase() == 'GET') {
			data = arg;
			} else {
			data = JSON.stringify(arg);
			}
			$.ajax({
			url: requestUrl,
			type: type,
			contentType: "application/json;charset=utf-8",
			data: data,
			})
			.done(function (result) {
			successcb && successcb(result);
			})
			.fail(function (xhn) {
			errorcb && errorcb(xhr);
			});
		}
	}
String.format = function () {
	if (arguments.length == 0)
	return null;
	var str = arguments[0];
	for (var i = 1;i<arguments.length; i++){
	var reg = new RegExp('\\{' + (i-1) +'\\}','gm');
	str = str.replace(reg, arguments[i]);
	}
	return str;
}	
/**
 * 是否为空
 * @param data
 * @returns
 */
function isNotEmpty(data){
	if(null!=data&& undefined != data && data.length>0){
		return true;
	}
	return false;
}	

function HashMap(){
	//定义长虔
	var length =0;
	//创建一个对象
	var obj = new Object();
	/* 判断 Map 是否为空
	*/
	this.isEmpty = function(){
	return length == 0;
	};
	/*
	* 判断对象中是否包含给定 Key
	*/
	this.containsKey=function(key){
	return (key in obj);
	};
	/* 判断对象中是否包含给定的 Value
	*/
	this.containsValue=function(value){
	for(var key in  obj){
		if(obj[key] == value){
			return true;
		}
		return false;
	}
	}
	
	
	this.put=function(key,value){
		if(!this.containsKey(key)){
			length++;
		}
		obj[key] = value;
	};
		/* 根据给定的 Key 获得 Value
		*/
	this.get=function(key){
		return this.containsKey(key)?obj[key]:null;
	};
		/*
		* 根据给定的 Key 删除一个值
		*/
		this.remove=function(key){
		if(this.containsKey(key)&&(delete obj[key])){
		length--;
		}
		};
		/*获得 Map 中的所有 Value
		*/
		this.values=function(){
		var _values= new Array();
		for(var key in  obj){
			_values.push(obj[key]);
		}
		return _values;
		};
		this.keySet= function(){
			var _keys = new Array();
			for(var key in obj){
				_keys.push(key);
			}
			return _keys;
		};
			/*
			* 获得 Map 的长度
			*/
			this.size = function(){
			return length;
			};
			/*
			*  清 ：SMap
			*/
			this.clear = function(){
			length =0;
			obj = new Object();
			};
}	
		
		
		