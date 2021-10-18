(function () {
    angular
        .module('market-app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/update_product/:productId', {
                templateUrl: 'update_product/update_product.html',
                controller: 'updateProductController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/reg', {
                templateUrl: 'reg/reg.html',
                controller: 'regController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })

            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        const contextPath = 'http://localhost:8189/market-core/';
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
        if (!$localStorage.webMarketGuestCartId) {
            $http.get(contextPath + 'api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.webMarketGuestCartId = response.data.value;
                });
        }
    }

})();
angular.module('market-app').controller('indexController', function ($rootScope, $http, $scope, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/market-core';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $http({
                        url: 'http://localhost:8190/market-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/merge',
                        method: 'GET',
                        headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
                    }).then(function (response) {
                    });

                }
            }, function errorCallback(response) {
                alert(response.data.messages);
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.registrationPage = function () {
        $location.path('/reg');
    }
});
