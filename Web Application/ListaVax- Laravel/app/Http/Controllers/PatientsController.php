<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class PatientsController extends Controller
{
    function patientForm(Request $request){
        return view('UserPage');
    }
    function add(Request $request){

        $request ->validate([
            'first_name' => 'required',
            'last_name' => 'required',
            'address' => 'required',
            'age' => 'required',
            'phone_number' => 'required',
            'dose_date' => 'required',
            'dose' => 'required',
            'vaccine' => 'required',
            'status' => 'required'
        ]);

        $query = DB::table('patients')->insert([
            'first_name' => $request->input('first_name'),
            'last_name' => $request->input('last_name'),
            'address' => $request->input('address'),
            'age' => $request->input('age'),
            'phone_number' => $request->input('phone_number'),
            'dose_date' => $request->input('dose_date'),
            'dose' => $request->input('dose'),
            'vaccine' => $request->input('vaccine'),
            'status' => $request->input('status'),
        ]);

        if($query){
            return back()->with('success', 'Data has been successfully inserted');
        }
        else{
            return back()->with('fail', 'Failed to insert data');
        }

    }

    function admin(){

        $data = array(
            'patientList'=> DB::table('patients')->get()
        );

        return view('AdminPage', $data);
    }
    
    function edit($id){
        $row = DB::table('patients')
                        ->where('id', $id)
                        ->first();
        $data = [
            'Info' => $row,
            'Title' => 'Edit Page'
        ];

            
        return view('edit', $data);
        
    }

    function update(Request $request){

        $request ->validate([
            'first_name' => 'required',
            'last_name' => 'required',
            'address' => 'required',
            'age' => 'required',
            'phone_number' => 'required',
            'dose_date' => 'required',
            'dose' => 'required',
            'vaccine' => 'required',
            'status' => 'required'
        ]);

        $updating = DB::table('patients')
                    ->where('id', $request->input('id'))
                    ->update([
                        'first_name' => $request->input('first_name'),
                        'last_name' => $request->input('last_name'),
                        'address' => $request->input('address'),
                        'age' => $request->input('age'),
                        'phone_number' => $request->input('phone_number'),
                        'dose_date' => $request->input('dose_date'),
                        'dose' => $request->input('dose'),
                        'vaccine' => $request->input('vaccine'),
                        'status' => $request->input('status'),
                    ]);
        return redirect('admin?auth=adminadmin');
           
    }

    function editStatus($id){
         $row = DB::table('patients')
                        ->where('id', $id)
                        ->first();
        $data = [
            'Info' => $row,
            'Title' => 'Edit Status Page'
        ];

            
        return view('editVaccineStatus', $data);
    }

    function vaccinated(Request $request){

        $request ->validate([
            'first_name' => 'required',
            'last_name' => 'required',
            'address' => 'required',
            'age' => 'required',
            'phone_number' => 'required',
            'dose_date' => 'required',
            'dose' => 'required',
            'vaccine' => 'required',
            'status' => 'required'
        ]);

        $query = DB::table('patients')->insert([
            'first_name' => $request->input('first_name'),
            'last_name' => $request->input('last_name'),
            'address' => $request->input('address'),
            'age' => $request->input('age'),
            'phone_number' => $request->input('phone_number'),
            'dose_date' => $request->input('dose_date'),
            'dose' => $request->input('dose'),
            'vaccine' => $request->input('vaccine'),
            'status' => $request->input('status'),
        ]);

        return redirect('admin?auth=adminadmin');

    }

    function delete($id){
        $delete = DB::table('patients')
                  ->where('id', $id)
                  ->delete();

        return redirect('admin?auth=adminadmin');
    }
    
}
