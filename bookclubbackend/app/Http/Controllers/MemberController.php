<?php

namespace App\Http\Controllers;

use App\Models\Member;
use App\Models\Payment;
use Carbon\Carbon;
use Illuminate\Http\Request;

class MemberController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return response()->json(Member::all(), 200);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $member = new Member();
        $gender = $request->get('gender');
        if ($gender != 'M' || $gender != 'F') {
            $member->fill($request->only([
                'name',
                'birth_date',
                'banned'
            ]));
        } else {
            $member->fill($request->only([
                'name',
                'gender',
                'birth_date',
                'banned'
            ]));
        }
        $member->save();

        return response()->json($member, 201);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Member  $member
     * @return \Illuminate\Http\Response
     */
    public function show(Member $member)
    {
        return response()->json($member, 200);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Member  $member
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Member $member)
    {
        $member->fill($request->only([
            'name',
            'gender',
            'birth_date',
            'banned'
        ]));
        $member->save();

        return response()->json($member, 200);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Member  $member
     * @return \Illuminate\Http\Response
     */
    public function destroy(Member $member)
    {
        $member->delete();
        return response()->json(1, 200);
    }

    public function pay($id) {
        $member = Member::find($id);
        if ($member == null) {
            return response()->json(["message" => "Nincs tag ilyen id-val"], 404);
        }

        // return response()->json(sizeof(Payment::where('paid_at', '>', 'NOW() - INTERVAL 30 DAY')->where('member_id', $id)->get()), 200);
        // $payment = "";
        $payment = Payment::where('paid_at', '>', 'NOW() - INTERVAL 30 DAY')->where('member_id', $id)->get();
        if (sizeof($payment) > 0) {
            return response()->json([
                "id" => $id,
                "message" => "Ezzel az id-val rendelkezo tag mar fizetett az elmult 30 napban"], 409);
        }
        else {
            $payment = new Payment();
            $payment->fill([
                'member_id' => $id,
                'amount' => 5000,
                'paid_at' => Carbon::now()
            ]);
            $payment->save();

            return response()->json($payment, 201);
        } 
    }
}
