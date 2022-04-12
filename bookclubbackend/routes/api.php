<?php

use App\Http\Controllers\MemberController;
use App\Http\Controllers\PaymentController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Monolog\Processor\MemoryProcessor;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::resource('/members', MemberController::class);
Route::post('/members/{id}/pay', [MemberController::class, 'pay']);

Route::resource('/payment', PaymentController::class);
