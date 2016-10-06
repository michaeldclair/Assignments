angular.module("AppMod", ["ngRoute"])
	.controller("AppCtrl", ['$http', '$routeParams', function($http, $routeParams) {
		var self = this;
		self.id = $routeParams.studentId;

		
		self.refresh = function() {
			for (var std of self.students) {
				std.vis = true;
			}
		};
		self.hide = function(studentId) {
			self.students[studentId].vis = false;
		};

		self.satcheck = function() {
		var minimumSat = document.getElementById('satbox').value;
			for (var std of self.students) {
					std.vis = true;
				if (std.sat < minimumSat) {
					std.vis = false;
				}
			}
		};

		$http.get('http://localhost:8080/student')
			.then(function(resp){
				self.students = resp.data;
				for (student of self.students) {
				student.vis = true;}
			},function(err) {

			});

		$http.get('http://localhost:8080/student/'+self.id)
			.then(function(resp){
				self.student = resp.data;
				student.vis = true;
			},function(err) {

			});

	}])
	.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'

		}).when('/student', {
			templateUrl: 'views/student.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/about', {
			templateUrl: 'views/about.view.html'

		}).when('/studentlist', {
			templateUrl: 'views/studentlist.view.html'

	//	}).when('/individualstudent', {
		}).when('/student/:studentId', {
			templateUrl: 'views/individualstudent.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		})
		.otherwise({redirectTo: '/'});

	}]); // end config