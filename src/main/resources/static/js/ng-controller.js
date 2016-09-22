angular.module('TIYAngularApp', [])
   .controller('SampleController', function($scope, $http) {

        $scope.getTodos = function() {
            console.log("About to go get me some data!");

            $http.get("http://localhost:8080/todos.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.todos = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.deleteTodo = function(todoID) {
            console.log("About to delete todo with ID " + todoID);

            $http.get("/deleteTodo.json?todoID=" + todoID)
                .then(
                    function success(response) {
                        console.log(response.data);
                        console.log("Todo deleted");
                        $scope.todos = response.data;
                    },
                    function error(response) {
                        console.log("unable to delete todo");
                    });
        };

        $scope.toggleTodo = function(todoID) {
            console.log("About to toggle todo with ID " + todoID);

            $http.get("/toggleTodo.json?todoID=" + todoID)
                .then(
                    function success(response) {
                        console.log(response.data);
                        console.log("Todo toggled");
                        $scope.todos = response.data;
                    },
                    function error(response) {
                        console.log("Unable to toggle todo");
                    });
        };

        $scope.addTodo = function() {
            console.log("About to add the following todo " + JSON.stringify($scope.newTodo));

            $http.post("/addTodo.json", $scope.newTodo)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.todos = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.newTodo = {};
        console.log("Controller initialized!!");
        $scope.getTodos();
    });