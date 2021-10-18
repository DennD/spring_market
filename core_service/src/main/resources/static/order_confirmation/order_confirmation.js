angular.module('market-app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market-core';

    $scope.loadCart = function () {
        $http({
            url: 'http://localhost:8190/market-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET',
            headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            $location.path('/');
        });
    };

    $scope.loadCart();
});