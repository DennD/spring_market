angular.module('market-app').controller('regController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/auth';

    $scope.registration = function () {
        if ($scope.user == null) {
            alert('Форма не заполнена');
            return;
        }
        $http.post(contextPath + '/api/v1/reg', $scope.user)
            .then(function successCallback (response) {
                $scope.user = null;
                alert('Пользователь успешно зарегистрирован');
                $location.path('/');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }
});