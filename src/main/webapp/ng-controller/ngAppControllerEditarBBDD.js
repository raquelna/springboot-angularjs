app.controller('ngAppControllerEditarBBDD',
['$scope', '$http', '$timeout', 'utilFactory',
  function ($scope, $http, $timeout, utilFactory)
  {
	
	(function()
			{
		
			
	 $http.post('/test',
		      {
		        table: 'valor enviado por POST utilizando el modelo TESTMODEL'
		      })
		      .then(function (response)
		      {
		       var data = response.data;
		     
		      
		       $scope.getbbdd = data;
		       
		      });
	 
			})();
	
	
	var select = document.querySelector('#listaTablas');
	select.addEventListener('change',
	  function(){
	    var selectedOption = this.options[select.selectedIndex];
	    console.log(selectedOption.value + ': ' + selectedOption.text);
	    
	  });
	
	
	
	
	
	
	
	
	
	
	
	
//	(function()
//			{
//		
//			
//	 $http.post('/getItems',
//		      {
//		        table: 'valor enviado por POST utilizando el modelo TESTMODEL'
//		      })
//		      .then(function (response)
//		      {
//		       var data = response.data;
//		     
//		      
//		       $scope.bbdd = data;
//		       
//		      });
//	 
//			})();
	
	
  }]);