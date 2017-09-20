app.controller('ngAppControllerEditarBBDD',
['$scope', '$http', '$timeout', 'utilFactory',
  function ($scope, $http, $timeout, utilFactory)
  {
	
	(function()
			{
		
			
	 $http.post('/getItems',
		      {
		        table: 'valor enviado por POST utilizando el modelo TESTMODEL'
		      })
		      .then(function (response)
		      {
		       var data = response.data;
		      
		       $scope.bbdd = data;
		      });
			})();
	
	
  }]);