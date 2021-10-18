angular.module('market-app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8190/market-cart';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET',
            headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
        }).then(function (response) {
            $scope.cart = response.data;
            console.log(response);
        });
    };

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId,
            method: 'GET',
            headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/decrement/' + productId,
            method: 'GET',
            headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/remove/' + productId,
            method: 'GET',
            headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart();
});