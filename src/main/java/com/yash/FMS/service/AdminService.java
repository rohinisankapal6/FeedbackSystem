package com.yash.FMS.service;

import com.yash.FMS.dto.AdminDTO;


public interface AdminService {

    AdminDTO authenticateAdmin(String username, String password);

}
