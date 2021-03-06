/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spatial4j.core.context.jts;

import com.spatial4j.core.shape.jts.JtsGeometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.Polygon;
import org.jeo.geom.Geom;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JtsSpatialContextTest {

    @Test
    public void testDatelineRule() {
        Polygon polygon = Geom.build().points(-179, -90, 179, -90, 179, 90, -179, 90).toPolygon();

        JtsSpatialContextFactory factory = new JtsSpatialContextFactory();
        factory.datelineRule = DatelineRule.width180;

        JtsSpatialContext ctx = factory.newSpatialContext();
        JtsGeometry shp = ctx.makeShape((Polygon) polygon.clone());
        assertTrue(shp.getGeom() instanceof GeometryCollection);

        factory.datelineRule = DatelineRule.none;
        ctx = factory.newSpatialContext();
        shp = ctx.makeShape(polygon);
        assertTrue(shp.getGeom() instanceof Polygon);
    }
}
