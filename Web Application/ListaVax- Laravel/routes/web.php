<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PatientsController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::get('/home', function () {
    return view('Homepage');
});

Route::get('admin', [PatientsController::class, 'admin'])->middleware('validateUser');

Route::get('patients', [PatientsController::class, 'patientForm']);
Route::post('add', [PatientsController::class, 'add']);

Route::get('edit/{id}', [PatientsController::class, 'edit']);
Route::post('update', [PatientsController::class, 'update'])->name('update');

Route::get('editStatus/{id}', [PatientsController::class, 'editStatus']);
Route::get('vaccinated', [PatientsController::class, 'vaccinated'])->name('vaccinated');

Route::get('delete/{id}', [PatientsController::class, 'delete']);