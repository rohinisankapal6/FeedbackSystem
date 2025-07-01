package com.yash.FMS.dao;

import com.yash.FMS.domain.Admin;


public interface AdminDAO {


    Admin getAdminByUsername(String username);
}
