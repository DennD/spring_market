angular.module('market-app').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market-core';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.showInfoProduct = function (id) {
        $http({
            url: contextPath + '/api/v1/products/' + id,
            method: 'GET'
        }).then(function successCallback(response) {
            alert("ID: " + response.data.id + " Название: " + response.data.title + " Стоимость: " + response.data.cost);
        }, function failureCallback(responce) {
            alert("Продукт отсутствует");
        });
    };

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + '/api/v1/products/' + 'delete',
            method: 'DELETE',
            params: {
                id: id
            }
        }).then(function successCallback(response) {
            alert("Продукт ID:" + response.config.params.id + " удален")
            $scope.loadProducts(currentPage);
        });
    };

    $scope.navToUpdateProductPage = function (productId) {
        $location.path('/update_product/' + productId);
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.addInCart = function (id) {
        $http({
            url: 'http://localhost:8190/market-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + id,
            method: 'GET',
            headers: {'username': $localStorage.webMarketUser ? $localStorage.webMarketUser.username : null}
        }).then(function (response) {
            console.log(response);
            alert("Продукт ID:" + id + " добавлен в корзину")
        });
    }

    $scope.loadProducts();
});