# paths nested under the tarp sysroot
TARP_SYSTEM_ROOT_DIR    := "/tarp"
TARP_SYSTEM_CONFIG_DIR  := "${TARP_SYSTEM_ROOT_DIR}/etc"
TARP_SYSTEM_LIB_DIR     := "${TARP_SYSTEM_ROOT_DIR}/lib"
TARP_SYSTEM_LUA_LIB_DIR := "${TARP_SYSTEM_ROOT_DIR}/lib/lua"
TARP_SYSTEM_SCRIPTS_DIR := "${TARP_SYSTEM_ROOT_DIR}/scripts"
TARP_SYSTEM_INCLUDE_DIR := "${TARP_SYSTEM_ROOT_DIR}/include"
TARP_SYSTEM_BIN_DIR     := "${TARP_SYSTEM_ROOT_DIR}/bin"
TARP_SYSTEM_CMAKE_DIR   := "${TARP_SYSTEM_ROOT_DIR}/lib/cmake"

# inherit globally
INHERIT += "staging"

SYSROOT_DIRS:append = " ${TARP_SYSTEM_ROOT_DIR}"

# other common variables
TARP_PACKAGE_SECTION := "tarp"

# installation 'macros', inspired by OpenWrt;
# use these to install files as appropriate
INSTALL_DIR   := "install -d -m0755"
INSTALL_BIN   := "install -m0755"
INSTALL_DATA  := "install -m0644"
INSTALL_CONF  := "install -m0600"

# ensure all paths necessary are created;
# this must be called before attempting to install a file
# on a path;
do_create_tarp_system_paths(){
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_CONFIG_DIR}
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_BIN_DIR}
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_SCRIPTS_DIR}
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_LIB_DIR}
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_LUA_LIB_DIR}
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_INCLUDE_DIR}
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_CMAKE_DIR}
}

# Replace occurences of $<tarp variable> in the input file with
# the expanded value as defined in current (this) file.
# For example all occurences of $TARP_SYSTEM_BIN_DIR will
# be replaced with the value of the $TARP_SYSTEM_BIN_DIR variable
# defined in this file.
#
# NOTE: the input file variables must use the '$variable' syntax,
# *not* the ${variable} syntax.
#
#$1 = absolute path to file to process
#
replace_tarp_vars(){
   bbplain "Substituting tarp variables in file: $1"
   local file="${1:?}"

   sed -i "s%\$TARP_SYSTEM_ROOT_DIR%${TARP_SYSTEM_ROOT_DIR}%g" "$file"
   sed -i "s%\$TARP_SYSTEM_BIN_DIR%${TARP_SYSTEM_BIN_DIR}%g" "$file"
   sed -i "s%\$TARP_SYSTEM_CONFIG_DIR%${TARP_SYSTEM_CONFIG_DIR}%g" "$file"
   sed -i "s%\$TARP_SYSTEM_LIB_DIR%${TARP_SYSTEM_LIB_DIR}%g" "$file"
   sed -i "s%\$TARP_SYSTEM_INCLUDE_DIR%${TARP_SYSTEM_INCLUDE_DIR}%g" "$file"
   sed -i "s%\$TARP_SYSTEM_CMAKE_DIR%${TARP_SYSTEM_CMAKE_DIR}%g" "$file"
}

# since yocto/bitbake has no reliable way of expressing a run-time
# dependency on a package version range, we must check here..
# Users must set LUA_VERSION_COMPAT to a string of space-separated
# versions e.g. LUA_VERSION_COMPAT='5.1 5.2 5.3' before calling
# this function.
def check_lua_version(d):
    import os
    import subprocess
    import glob

    d = d.getVar
    STAGING_DIR_TARGET=d("STAGING_DIR_TARGET")
    BINDIR=d("bindir")
    target_dir = STAGING_DIR_TARGET + "/" + BINDIR
    bb.note(f"STAGING_DIR_TARGET={STAGING_DIR_TARGET}; bindir={BINDIR}")
    bb.note(f"Looking for Lua interpreter in {target_dir}")
    files = glob.glob(f"{target_dir}/lua*")
    bb.note(f"Lua Interpreter candidates: {[os.path.abspath(x) for x in files]}")
    target_lua = target_dir + "/lua"

    if not os.path.isfile(target_lua):
        bb.fatal(f"Lua binary not found at: {target_lua}")

    result = subprocess.run(["strings", target_lua], capture_output=True, text=True)
    lines = result.stdout.splitlines()

    match = next((l for l in lines if "Lua 5." in l), None)
    if not match:
        bb.fatal("Could not find Lua version string in binary")

    # good_lua_versions = ["5.3", "5.4"]
    good_lua_versions = d("LUA_VERSION_COMPAT").split()
    if len(good_lua_versions) > 0:
        found = False
        for version in good_lua_versions:
            if f'Lua {version}' in match:
                found=version
                break
        if not found:
            bb.fatal(f"Incompatible Lua version detected: {match}. Required: {good_lua_versions}")
        bb.note(f"Lua version OK: Lua{version}")


python do_check_lua_version(){
    check_lua_version(d)
}

