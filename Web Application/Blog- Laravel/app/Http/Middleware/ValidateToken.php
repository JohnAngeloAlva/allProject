<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class ValidateToken
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle(Request $request, Closure $next)
    {
        
        $secretToken = 'John Angelo Alva';
        
        if($request->input('token') !== $secretToken){
            
            return redirect('home');
        }

        return $next($request);
    }
}
