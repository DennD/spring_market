angular.module('market-app').controller('regController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market-core';

    $scope.registration = function () {
        if ($scope.user == null) {
            alert('Форма не заполнена');
            return;
        }
        $http.post(contextPath + '/reg', $scope.user)
            .then(function successCallback (response) {
                $scope.user = null;
                alert('Пользователь успешно зарегистрирован');
                $location.path('/');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }
});