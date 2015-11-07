/// <reference path="../../../typings/angularjs/angular.d.ts"/>
angular.module('app', ['ngMaterial'])

.controller('AplicacaoCtrl', AplicacaoCtrl)

.controller('DemoCtrl', DemoCtrl)

.service('BuscasService', function ($http, $q) {

     this.getData = function() {
         return $http.get('data.json');
     };

 });



function AplicacaoCtrl($scope, $http, BuscasService) {

	$scope.integrantes = [];
	$scope.mensagem = "Pépeee! Já coloquei o disquete!";
	
	BuscasService.getData()
		.then(
		function(response) {
			$scope.integrantes = response.data;
		},
		function(response) {
			console.log('Erro ao requisitar a lista.');
		});
	
};

function DemoCtrl($scope) {

	$scope.user = {
			title: 'Developer',
			email: 'ipsum@lorem.com',
			firstName: '',
			lastName: '',
			company: 'Google',
			address: '1600 Amphitheatre Pkwy',
			city: 'Mountain View',
			state: 'CA',
			biography: 'Loves kittens, snowboarding, and can type at 130 WPM.\n\nAnd rumor has it she bouldered up Castle Craig!',
			postalCode: '94043'
		};
		$scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
			'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
			'WY').split(' ').map(function (state) {
				return { abbrev: state };
			})

};
