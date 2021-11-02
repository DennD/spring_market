angular.module('market-app').controller('profileController', function ($scope, $http, $location) {

    $scope.loadOrders = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
            console.log($scope.orders);
        });
    };

    $scope.loadMyProfile = function () {
        $http({
            url: 'http://localhost:5555/auth/api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };

    $scope.checkPaymentOut = function (orderId) {
        $location.path('/order_pay/' + orderId)
    }

    $scope.disabledCheckPaymentOut = function () {
        alert("Заказ уже оплачен");
    }

    $scope.loadOrders();
    $scope.loadMyProfile();
});