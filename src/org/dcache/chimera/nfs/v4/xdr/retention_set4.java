/*
 * Copyright (c) 2009 - 2012 Deutsches Elektronen-Synchroton,
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this program (see the file COPYING.LIB for more
 * details); if not, write to the Free Software Foundation, Inc.,
 * 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.dcache.chimera.nfs.v4.xdr;
import org.dcache.xdr.*;
import java.io.IOException;

public class retention_set4 implements XdrAble {
    public boolean rs_enable;
    public uint64_t [] rs_duration;

    public retention_set4() {
    }

    public retention_set4(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        xdrDecode(xdr);
    }

    public void xdrEncode(XdrEncodingStream xdr)
           throws OncRpcException, IOException {
        xdr.xdrEncodeBoolean(rs_enable);
        { int $size = rs_duration.length; xdr.xdrEncodeInt($size); for ( int $idx = 0; $idx < $size; ++$idx ) { rs_duration[$idx].xdrEncode(xdr); } }
    }

    public void xdrDecode(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        rs_enable = xdr.xdrDecodeBoolean();
        { int $size = xdr.xdrDecodeInt(); rs_duration = new uint64_t[$size]; for ( int $idx = 0; $idx < $size; ++$idx ) { rs_duration[$idx] = new uint64_t(xdr); } }
    }

}
// End of retention_set4.java